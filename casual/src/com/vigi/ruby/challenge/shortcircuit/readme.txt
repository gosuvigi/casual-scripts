Given a closed electrical circuit, we need to identify the redundant elements.
For the sake of simplicity, we shall assume only resistors between various
points. Electricity will flow through the path of least resistance! 
Source of electricity is A and the end-point is G.

The idea of the program is to find the redundant resistors.

As shown in the diagram we can ‘translate’ load between points into any 
simple data structures. For example:

[
   [ A, B, 50],
   [ A, D, 150],
   [ B, C, 250],
   [ B, E, 250],
   [ C, E, 350],
   [ C, D, 50],
   [ C, F, 100],
   [ D, F, 400],
   [ E, G, 200],
   [ F, G, 100],
]

Feel free to use ANY other data structure as long as assumptions are simple
and understandable. The source and the end-point may be hard-coded in the 
script or can be taken as command line parameters, whichever you feel is
convenient.

In the above example, the output expected MUST be the following array 
of arrays:

[
  [ 'A', 'B', 50 ],
  [ 'B', 'C', 250],
  [ 'B', 'E', 250],
  [ 'C', 'E', 350],
  [ 'D', 'F', 400],
  [ 'E', 'G', 200],
]
