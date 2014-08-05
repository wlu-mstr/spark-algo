spark-algo
==========

Use spark to implement some algorithms.

### [connected component algorithm for undirected graph](http://en.wikipedia.org/wiki/Connected_component_(graph_theory)).

Source codes under connected-component contains a Java programe which verifies the algorithm and a spark version implemented by following the algorithm.

The idea of the algorithm contains following aspects:

* each connected component is represented by one special node (say, it is the center of the cc), here we choose the one with minimal vertex value: 

```
(min1, [t1, t2, t3 ,...])
(min2, [t21, t22, t23 ,...])
(min2, [t31, t32, t33 ,...])
```

* in each round of iteration, we will try to find more vertex for existing cc. Idea is like:

input:
```
(1, [2])
(2, [3])
```
reverse and merge:
```
(1, [2])    (2, [1])
(2, [3])    (3, [2])
```
group by key
```
(1, [2])
(2, [1, 3])
(3, [2])
```
we can find in the second pair, 1 < 2 < 3. which means 2 is in the middle of 1 and 3, so we should connect them together and use 1 as the center.

Here is the logic:
```
Given (fromNode, [toNode1, toNode2, ...])

min = min{toNode1, toNode2, ...}
max = max{toNode1, toNode2, ...}

if fromNode < min 
    then output [(fromNode, toNode1), (fromNode, toNode2), ...]

else if fromNode > max 
    then output nothing

else 
    output [(min, toNode1), (min, toNode2),...] + [(min, fromNode)] - [min, min]

```
