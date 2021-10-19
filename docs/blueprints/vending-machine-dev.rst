.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Vending Machine Blueprint Applications Interface and Protocol
#############################################################

.. contents::
   :depth: 4

Communication Protocol
**********************

The "Vending Machine" blueprint will take advantage of two applications: a UI
and an "IO Controller". These applications will exchange messages over a
defined interface using a specific protocol. For the scope of this
specification, the communication will happen over plain WebSockets/TCP.

Specification
-------------

In terms of roles, we have a client and a server. The "IO Controller" acts as a
server while the "UI" process, as a client.

As a minimum client/server specification, the applications will exchange
messages as per the following diagram:

.. code-block::

   ┌────────────────┐     selection      ┌─────────────────────┐
   │                ├───────────────────>┤                     │
   │                │                    │                     │
   │                │     deliver        │ IO                  │
   │ UI Application ├───────────────────>┤ Control Application │
   │    (client)    │                    │      (server)       │
   │                │     delivered      │                     │
   │                │◄───────────────────┤                     │
   └────────────────┘                    └─────────────────────┘

Static information can be set in configuration files shared between both
applications (e.g. for items name, a timeout for simulated actions, number of
item slots, etc.).

Server application is made on generic concepts inspired by WoT/WebThings:

- properties: set a *selection* of products
  - selection is a fixed size array and items are identified from indices in
  this array while the values represent the associated item quantity
- actions: request a *deliver* order
  - order also contain the current selection as a parameter
- events: *delivered* event will notify that the *deliver* action was finished
  - event is delivered based on the *addEventSubscription* subscription message

Thoses objects will be used through websockets's messages on default endpoint
(ie: `<ws://localhost:8888/>`).

Client request's payloads are formatted using JSON structures. Below there is
an example for each of the types defined:

Properties
==========

.. code-block:: json

    {
      "messageType": "setProperty",
      "data": {
          "selection": [0, 0, 0, 1]
      }
    }

Actions
=======

.. code-block:: json

    {
      "messageType": "requestAction",
      "data": {
        "deliver": {
          "input": {
            "selection": [0, 1, 0, 0]
          }
        }
      }
    }

Events
======

The client needs to send a subscription message once and listen from server's
event messages:

.. code-block:: json

    {
      "messageType": "addEventSubscription",
      "data": {
        "delivered": {}
      }
    }

.. code-block:: json

    {
      "messageType": "event",
      "data": {
        "delivered": {}
      }
    }

Inter-application message flow
------------------------------

The UI and Control applications will adhere to the message schema defined
above. The message flow is described as it follows:

.. code-block::

   ┌────┐                      ┌────┐
   │    │                      │    │
   │    │                      │    │
   │    │      selection       │    │
   │    ├─────────────────────►│    │
   │    │                      │    │
   │    │      selection       │    │
   │    ├─────────────────────►│    │
   │    │                      │    │
   │    │      selection       │    │
   │    ├─────────────────────►│    │
   │    │                      │    │
   │    │                      │    │
   │    │ [...]                │    │
   │    │                      │    │
   │ UI │ [...]                │ IO │
   │    │                      │    │
   │    │                      │    │
   │    ├─────────────────────►│    │
   │    │                      │    │
   │    │                      │    │
   │    │        deliver       │    │
   │    ├─────────────────────►│    │
   │    │                      │    │
   │    │       delivered      │    │
   │    │◄─────────────────────┤    │
   │    │                      │    │
   │    │                      │    │
   │    │                      │    │
   │    │                      │    │
   │    │                      │    │
   └────┘                      └────┘

Detailed example flow:

Firstly, client is intializing by subscribing for server's future **"delivered" events**:

.. code-block:: json

    {
      "messageType": "addEventSubscription",
      "data": {
        "delivered": {}
      }
    }

Client's application is setting an empty selection on server and then UI will
wait for user inputs:

.. code-block:: json

    {
      "messageType": "setProperty",
      "data": {
        "selection": [0, 0, 0, 0]
      }
    }

User selects one product (one of type "1"):

- UI will be updated accordigly
- The client process makes a request to the server to set **selection
  "property"**

.. code-block:: json

   {
     "messageType": "setProperty",
     "data": {
       "selection": [0, 1, 0, 0]
     }
   }

The IO Controller will turn on the associated LEDs to show another visual
indication.

Then the user decides to add 1 more product of type "3":

.. code-block:: json

   {
     "messageType": "setProperty",
     "data": {
       "selection": [0, 1, 0, 1]
     }
   }

The user confirms the order by pressing the relevant UI element, then a
**"deliver" action"** is sent from client to the server:

.. code-block:: json

   {
     "messageType": "requestAction",
     "data": {
       "deliver": {
         "input": {
           "selection": [0, 1, 0, 1]
         }
       }
     }
   }

The UI application will be blocked until ready or timeout is reached:

- watchdog/timeout timer starts on UI/client
- UI waits for the **delivered** event

Processing is done server-side and **delivered event** is triggered:

.. code-block:: json

   {
     "messageType": "event",
     "data": {
       "delivered": {}
     }
   }

The UI is unblocked and ready for new selection (it should reinitialized to
empty).

If no "delivered" event after a defined timeout, the UI will display an "out of
order" message and show a "reset" button to refresh for the next order.

Software Dependencies Versions
------------------------------

Oniro Project supports the following libraries for message encoding/decoding/parsing and
the communication protocol:

* `libwebsockets <https://libwebsockets.org/>`_ 4.0.1

* `cjson <https://github.com/DaveGamble/cJSON/>`_ 1.7.13 (to be upgraded to
  1.7.14 for OpenHarmony convergence)

* `json-c <https://github.com/json-c/json-c>`_ 0.13.1

Extra software could be integrated if needed:

* `libmicrohttpd <https://git.gnunet.org/libmicrohttpd.git/tree/src/include/microhttpd.h>`_

For prototyping purposes server can be easily implemented using
`webthings framework <https://webthings.io/>`_.


Message schema
--------------

Selection Message Schema
========================

The schema for the "selection" messages is:

.. code-block:: json

   {
     "$schema": "http://json-schema.org/draft-07/schema",
     "$id": "http://example.com/example.json",
     "type": "object",
     "title": "The root schema",
     "description": "The root schema comprises the entire JSON document.",
     "default": {},
     "examples": [
       {
         "messageType": "setProperty",
         "data": {
           "selection": [
             0,
             1,
             0,
             0
           ]
         }
       }
     ],
     "required": [
       "messageType",
       "data"
     ],
     "properties": {
       "messageType": {
         "$id": "#/properties/messageType",
         "type": "string",
         "title": "The messageType schema",
         "default": "",
         "examples": [
           "setProperty"
         ]
       },
       "data": {
         "$id": "#/properties/data",
         "type": "object",
         "title": "The data schema",
         "default": {},
         "examples": [
           {
             "selection": [
               0,
               1,
               0,
               0
             ]
           }
         ],
         "required": [
           "selection"
         ],
         "properties": {
           "selection": {
             "$id": "#/properties/data/properties/selection",
             "type": "array",
             "title": "The selection schema",
             "default": [],
             "examples": [
               [
                 0,
                 1
               ]
             ],
             "additionalItems": true,
             "items": {
               "$id": "#/properties/data/properties/selection/items",
               "anyOf": [
                 {
                   "$id": "#/properties/data/properties/selection/items/anyOf/0",
                   "type": "integer",
                   "title": "The first anyOf schema",
                   "default": 0,
                   "examples": [
                     0,
                     1
                   ]
                 }
               ]
             }
           }
         },
         "additionalProperties": true
       }
     },
     "additionalProperties": true
   }

Deliver Message Schema
======================

The schema for the "deliver" messages is:

.. code-block:: json

   {
     "$schema": "http://json-schema.org/draft-07/schema",
     "$id": "http://example.com/example.json",
     "type": "object",
     "title": "The root schema",
     "description": "The root schema comprises the entire JSON document.",
     "default": {},
     "examples": [
       {
         "messageType": "requestAction",
         "data": {
           "deliver": {
             "input": {
               "selection": [
                 0,
                 1,
                 0,
                 0
               ]
             }
           }
         }
       }
     ],
     "required": [
       "messageType",
       "data"
     ],
     "properties": {
       "messageType": {
         "$id": "#/properties/messageType",
         "type": "string",
         "title": "The messageType schema",
         "default": "",
         "examples": [
           "requestAction"
         ]
       },
       "data": {
         "$id": "#/properties/data",
         "type": "object",
         "title": "The data schema",
         "default": {},
         "examples": [
           {
             "deliver": {
               "input": {
                 "selection": [
                   0,
                   1,
                   0,
                   0
                 ]
               }
             }
           }
         ],
         "required": [
           "deliver"
         ],
         "properties": {
           "deliver": {
             "$id": "#/properties/data/properties/deliver",
             "type": "object",
             "title": "The deliver schema",
             "default": {},
             "examples": [
               {
                 "input": {
                   "selection": [
                     0,
                     1,
                     0,
                     0
                   ]
                 }
               }
             ],
             "required": [
               "input"
             ],
             "properties": {
               "input": {
                 "$id": "#/properties/data/properties/deliver/properties/input",
                 "type": "object",
                 "title": "The input schema",
                 "default": {},
                 "examples": [
                   {
                     "selection": [
                       0,
                       1,
                       0,
                       0
                     ]
                   }
                 ],
                 "required": [
                   "selection"
                 ],
                 "properties": {
                   "selection": {
                     "$id": "#/properties/data/properties/deliver/properties/input/properties/selection",
                     "type": "array",
                     "title": "The selection schema",
                     "default": [],
                     "examples": [
                       [
                         0,
                         1
                       ]
                     ],
                     "additionalItems": true,
                     "items": {
                       "$id": "#/properties/data/properties/deliver/properties/input/properties/selection/items",
                       "anyOf": [
                         {
                           "$id": "#/properties/data/properties/deliver/properties/input/properties/selection/items/anyOf/0",
                           "type": "integer",
                           "title": "The first anyOf schema",
                           "default": 0,
                           "examples": [
                             0,
                             1
                           ]
                         }
                       ]
                     }
                   }
                 },
                 "additionalProperties": true
               }
             },
             "additionalProperties": true
           }
         },
         "additionalProperties": true
       }
     },
     "additionalProperties": true
   }

Delivered Message Schema
========================

The schema for the "delivered" messages is:

.. code-block:: json

   {
     "$schema": "http://json-schema.org/draft-07/schema",
      "type": "object",
      "title": "The root schema",
      "description": "The root schema comprises the entire JSON document.",
      "default": {},
      "examples": [
        {
          "messageType": "event",
          "data": {
            "delivered": {}
          }
        }
      ],
      "required": [
        "messageType",
        "data"
      ],
      "properties": {
        "messageType": {
          "$id": "#/properties/messageType",
          "type": "string",
          "title": "The messageType schema",
          "description": "An explanation about the purpose of this instance.",
          "default": "",
          "examples": [
            "event"
          ]
        },
        "data": {
          "$id": "#/properties/data",
          "type": "object",
          "title": "The data schema",
          "description": "An explanation about the purpose of this instance.",
          "default": {},
          "examples": [
            {
              "delivered": {}
            }
          ],
          "required": [
            "delivered"
          ],
          "properties": {
            "delivered": {
              "$id": "#/properties/data/properties/delivered",
              "type": "object",
              "title": "The delivered schema",
              "description": "An explanation about the purpose of this instance.",
              "default": {},
              "examples": [
                {}
              ],
              "required": [],
              "additionalProperties": true
            }
          },
          "additionalProperties": true
        }
      },
      "additionalProperties": true
  }

Previous event will be notified if the client sends a subscription message:

.. code-block:: json

   {
     "$schema": "http://json-schema.org/draft-07/schema",
     "$id": "http://example.com/example.json",
     "type": "object",
     "title": "The root schema",
     "description": "The root schema comprises the entire JSON document.",
     "default": {},
     "examples": [
       {
         "messageType": "addEventSubscription",
         "data": {
           "delivered": {}
         }
       }
     ],
     "required": [
       "messageType",
       "data"
     ],
     "properties": {
       "messageType": {
         "$id": "#/properties/messageType",
         "type": "string",
         "title": "The messageType schema",
         "description": "An explanation about the purpose of this instance.",
         "default": "",
         "examples": [
           "addEventSubscription"
         ]
       },
       "data": {
         "$id": "#/properties/data",
         "type": "object",
         "title": "The data schema",
         "description": "An explanation about the purpose of this instance.",
         "default": {},
         "examples": [
           {
             "delivered": {}
           }
         ],
         "required": [
           "delivered"
         ],
         "properties": {
           "delivered": {
             "$id": "#/properties/data/properties/delivered",
             "type": "object",
             "title": "The delivered schema",
             "description": "An explanation about the purpose of this instance.",
             "default": {},
             "examples": [
               {}
             ],
             "required": [],
             "additionalProperties": true
           }
         },
         "additionalProperties": true
       }
     },
     "additionalProperties": true
   }

Current assumptions
-------------------

* Both of the applications (server/client, "UI"/"IO Controller" are running on
  the same, Linux-based target.
* The quantity of a selection is maximum "1". This means that the selection
  array can contain values of 0 or 1.
* The availability from the perspective of the "IO Controller" is infinite.
