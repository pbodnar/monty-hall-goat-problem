# About

This repo contains a simple Java class which simulates the [Monty Hall problem](https://en.wikipedia.org/wiki/Monty_Hall_problem). It simply outputs results of repeated simulated plays of the game.

So yes, it is yet another repo dedicated to this interesting problem / puzzle. :)

But, unlike code in some of the other repos, this repo code is as concise and readable as possible. The code also includes comments that try to explain why one of the two playing strategies is definitely better, so that even non-programmers or non-mathematicians can easily understand what the hell is going on with the goats. ;)

# Running the code

If just reading through the code is not enough, you can compile and run it. Of course, you can do that either from your favorite Java IDE. Or you can do it from the command line, using the standard Java SDK tools like this:

```
$ javac Main.java

$ java Main
play #1: win1: true, win2: false
...
play #99: win1: false, win2: true
play #100: win1: false, win2: true
wins1 (without switch): 28, wins2 (with switch): 72
```
