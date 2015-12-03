# Stage 2

## Problem specs

Given a maze and a starting position on `stdin`, traverse the maze and find the gold hidden throughout.
With every stage, the code is to print out a position, and the testing code will then send `OK` if that
is a valid position to move to from the current location. It will also give positions of the first gold
visible in every direction. Once all of the gold has been found, it will send the `DONE` command,
indicating the program is done and should terminate.

## Building

To build the code, simply run
```
javac *.java
```
in this directory.

## Running

Once built, the code can be run with the following command:
```
java Stage2
```

## Usage

Don't worry about command line parameters; input comes through stdin.

Once started, input a maze for the code to traverse, as well as a starting point. For example:
```
+-+-------+-------+-+
| |       |       | |
| +------ | +-+-+-+ |
| |         | | |   |
| | ----+ --+ | | --+
|       |           |
+---- +-+ | --+ | +-+
|     | | |   | | | |
| +-- | | | | | | | |
| |     | | | | |   |
+-+-- | +-+-+ +-+-+ |
|     |   |     | | |
+-- --+ | | | --+ +-+
|     | | | |       |
| | --+-+-+ +-+ | | |
| |       |   | | | |
| | | ----+ +-+-+ | |
| | |     | |     | |
+-+ | | --+-+-- | +-+
|   | |     |   |   |
+---+-+-----+---+---+
(0,0)
```

At that point, the code will start to traverse the maze (inefficiently, I know, but it will at least get to every spot).
Technically, you're supposed to input `OK` when a movement is valid, as well as tell the code where any visible gold is,
but I was running out of time, so I just made the code ignore any input like that and just go to every spot until it recieves
`DONE`. If it gets to every reachable spot without recieving the `DONE` command, it will just hang out in the last spot,
since it's not done, but can't continue. This version has extra debug info, so it will print not only the current position,
but the position it's on the way to, and the current state of the maze. In the current state printout, `*` indicates a cell
that has been visited, and `#` indicates the current cell. You can watch it crawl around the maze by just holding down enter,
which is kind of entertaining, and about the only thing this code is really good for, since I've already submitted the code
from the initial commit (which does not have the debug info, as that would mess everything up).
