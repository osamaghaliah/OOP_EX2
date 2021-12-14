# OOP_EX2
Implementation: A directional weighted graph that includes set of nodes and edges:

  Nodes: Each node includes - a certain key, location, weight and info.
  Edges: Each edge includes - source, weight and destination.

  Graph: The graph was implemented via 3 hashmaps:
    First hashmap represents our graph's nodes - The keys are the numbers of the nodes and the values are the nodes themselves.
    Second hashmap represents our graph's edges - However, its values are a hashmap of the edges attributes.

  Graph's Algorithms: Initialization of a graph with methods which check if
		the graph is connected or wether we can return a shortest path among nodes, save and load, tsp and center... etc.

Methods, functions and complexity: 
	We used these structures of hashmaps so we can get
	complexity of O(1) - for the majority of the methods. In addition. Dykstra algorithm was also used
  in order to grant us the use of millions of nodes. Our codes were pretty much effective and convenient to use.
  Testers were professionally made, the whole functions passed its tester successfully.
  Eventaully, we managed to create a unique GUI (Graphical User Interface) that draws the input graphs -
  G1.json, G2.json, G3.json - by using an our own Ex2.jar file that runs via Command Prompt by using the following command:
  java -jar Ex2.jar <json file>
  it exists in our/artifacts
