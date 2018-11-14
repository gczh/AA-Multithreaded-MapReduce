# AA-Multithreaded-MapReduce
Multithreading MapReduce for Architectural Analysis Mod

# Background / Brief Description Of Project


# Justification For Multi-Threading

We are writing a multithreaded implementation of Map Reduce based on the following assumptions:

1. There are resource constraints and we do not want to use a multi-server setup to parallize the Map Reduce operations.
2. This is a simple proof-of-concept example where the author tries to put whatever (or what little) he could learn and understand about Map Reduce and concurrency into practice.
3. Mok is the best instructor in Singapore, or perhaps the world.

# Performance

All these codes were tested on a quad-core 2.3 GHz Macbook Pro 2018. So the performance time may differ largely for a dual-core machine. However, it should still be obvious that there is a performance improvement.

# Transactional Integrity

# Evidence Of Exploration

# Innovation

# Adherence To Good Coding Practices

# References & Acknowledgement

I found it significantly easier to understand functional programming examples written in Lisp, Racket and similar functional languages.

# Getting Started

## Prerequisites

1. At least 1.82 GB of space on your computer

    This is because the `generate.bat` file creates 10 datasets each sized at 182 MB. There will 10 of the same files, thus adding up to 1.82 GB of space for the test dataset. We recommend using a large dataset to really see the performance improvements of ~60% in execution time, which is measured using `Stopwatch.java`.

2. Java 8 and above

## Run The Program

1. To get started, you need to first run `generate.bat` to create the sample large dataset for testing the Map Reduce operation.

2. Then run `mokreduce_bad.bat` to test the slower, simpler implementation of Map Reduce.

    a. When prompted for a folder, put in `inputs`
    
    b. Run and profit

3. Lastly, run `mokreduce_good.bat` to test the optimized version of Map Reduce that uses concurrency.

    a. When prompted for a folder, put in `inputs`
    
    b. Run and profit

