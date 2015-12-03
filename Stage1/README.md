# Stage 1

## Problem Specs

Given command line arguments about the width and height of a desired maze, as well as directions for the
length of the solution path, the program is to generate a maze that meets the requirements.

To be honest, I've forgetten how the path length arguments work, since I never implemented them. There was
stuff for making the path length less than, greater than, or equal to some length, but I figured I wouldn't
bother with that to save time. So, the usage for my version is going to be a bit simplfied.

## Building

To build the code, simply run
```
javac *.java
```
in this directory

## Running

Once built, the code can be run with the folling command:
```
java Stage1 <width> <height>
```

## Usage

Pretty simple - just run the above command with a width and a height and it generates a maze. Admittedly,
the maze is almost always solved by just going down and to the right, with maybe some small twists along
the way. If you want to see a better command-line maze project, check out my [ncurses-based maze project](evan1026/ncurses-maze).
