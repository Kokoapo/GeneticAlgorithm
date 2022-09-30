# Genetic Algorithm

A simple Genetic Algorithm made during `Combinatorial Optimization` classes as a `Computer Science` major study source

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Objective

The objective of this algorithm is to use a given function and a certain range (minimum and maximum values) to get the highest `fitness` (or function result) possible.
The Algorithm creates 100 generations in total until it stops, if you want to change this just edit the value on `while (genCount <= 100);` at line `34`.

- `Fitness function`: X^2 - Y
- `Range`: 0 =< X =< 100 ; 0 =< Y =< 100
- `Best outcome possible`: X = 100 ; Y = 0 ; Fitness = 10000

> After some tests I realized that the `X` is working like expected, always having a final result greater or equal than 90, but the `Y` is being too inconsistent, usually having final results inside the `0 =< Y =< 93` range, an explanation for this might be that `Y` is too insignificant, not impacting much on the `fitness` for having a low value on the function.
> Some options to solve the issue ahead would be decreasing the `X` value or increasing the `Y` value on the `fitness function`.  