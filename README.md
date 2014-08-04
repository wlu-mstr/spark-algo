spark-algo
==========

User spark to implement some algorithms.

h2. connected component algorithm for undirected graph.

Source codes under connected-component contains a Java programe which verifies the algo and a spark version implemented by following the algorithm.

The idea of the algorithm contains following aspects:
(1) each connected component is represented by one special node, here we choose the one with minimal vertex value;
(2) in each round of iteration, we will try to find more vertex for existing cc. for all the vertex connected to a same vertex,
(fromNode, [toNode1, toNode2,...])
`
min = min{toNode1, toNode2, ...}
max = max{toNode1, toNode2, ...}
if fromNode < min then output [(fromNode, toNode1), (fromNode, toNode2), ...]
else if fromNode > max then output nothing
else output [(min, fromNode), (min, toNode1), (min, toNode2),...] - [min, min]
`
