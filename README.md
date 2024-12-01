# Advent of Code 2023 - Day 1: Historian Hysteria

## Problem Description

[Advent of Code Day 1](https://adventofcode.com/2023/day/1) tasks us with reconciling two lists of location IDs held by Elvish Senior Historians. The lists are supposed to be the same, but they have discrepancies.

**Part 1:** We need to calculate the total distance between the lists by pairing up the numbers (smallest to smallest, second smallest to second smallest, and so on) and summing the absolute differences between the pairs.

**Part 2:** We need to find a similarity score between the lists. This score is calculated by multiplying each number in the first list by the number of times it appears in the second list, and then summing up these products.

## Solution

This solution is implemented in Kotlin and uses idiomatic features for conciseness and efficiency.

### Input Loading

The `load` function reads the input file, splits each line into two numbers, and adds them to the `s1` (first list) and `s2` (second list) mutable lists.

### Part 1: Calculating Total Distance

The `part1` function sorts both lists, then uses the `zip` function to pair up corresponding elements. The absolute difference between each pair is calculated and summed to get the total distance.

### Part 2: Calculating Similarity Score

The `part2` function uses `groupingBy` and `eachCount` to create a map where keys are the numbers in the second list (`s2`) and values are the number of times each number appears. It then iterates through the first list (`s1`), multiplying each number by its count in the map (or 0 if it's not present) and summing the results to get the similarity score.

### Running the Solution

The `main` function loads the input file, calls `part1` and `part2` to get the results, and prints them to the console.

## Explanation

**Part 1:**
- The solution sorts both lists to ensure that corresponding elements are being compared.
- The `zip` function efficiently creates pairs of elements from the two lists.
- The `sumOf` function elegantly calculates the total distance by iterating over the pairs and adding up the absolute differences.

**Part 2:**
- The `groupingBy` and `eachCount` functions create a map that stores the frequency of each number in the second list.
- The solution iterates through the first list and uses the map to look up the frequency of each number.
- The similarity score is calculated by multiplying each number in the first list by its frequency in the second list and summing the products.

**Overall, this solution is concise, efficient, and idiomatic, leveraging Kotlin's features to solve the problem elegantly.**