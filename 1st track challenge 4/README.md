## 1st track - challenge 4

> 4. Verify that your layer passes compatibility tests/uses best practices. Run yocto-check-layer results (log).
> Result: Commit a text file or screenshot.

Running the `yocto-check-layer` script on my layer (which can be seen in the folder "1st track challenge 3") results in an error, thus in a FAIL. After asking the mentors for help, they suggested to add the flag `--without-software-layer-signature-check` but even this didn't resolved the problem.

After further investigating the problem, mentors said that's a regression problem on a layer, that has nothing to do with my particular layer.

In the file `yocto-output.txt` you can see the errors mentioned above. Actually there are three different runs of the script:
- the first one is without any flag
- the second and the third show different errors with the same command (the two errors appears for no specific reason)
