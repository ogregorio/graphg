# Graphg CLI #

This application aims to create adjacency matrix that represent direct graphs with and without labels.

The application was developed in Java, a pre-release is available here, on Github.

## Use ##

### CREATE commands ###
Type:

To create a labeled graph.

```shell
graphg >> CREATE WITH_LABEL
```
To create a not labeled graph.
```shell
graphg >> CREATE NO_LABEL
```
To create a not labeled graph undirected.
```shell
graphg >> CREATE NO_LABEL_UNDIRECTED
```
___
### ADD commands ###
#### Without labels ####
(*just work with NO_LABEL and NO_LABEL_UNDIRECTED graphs*)

Type:

to implement a vertex

```shell
graphg >> ADD VERTEX VERTEX1
```

to implement a edge
```shell
graphg >> ADD EDGE VERTEX1 VERTEX2
```

#### With labels ####
(*just work with WITH_LABEL graphs*)

Type:

to implement a vertex

```shell
graphg >> ADD VERTEX LABEL
```

to implement a edge

```shell
graphg >> ADD EDGE VERTEX1 VERTEX2 LABEL
```
___

### COUNT commands ###
(*just work with NO_LABEL_UNDIRECTED graphs*)

Type:

to show all cycles in graph with any size

```shell
graphg >> COUNT CYCLES
```

to show all cycles in graph with specific size

```shell
graphg >> COUNT CYCLES 4
```

___

### PRINT command ###

Type:

to print graph in display
****

```shell
graphg >> PRINT
```

## PGM command ##

Type:

to segment the background/foreground of an image using network flow graph and min cut

``` shell
graphg >> PGM /origin_source /destiny_source.
```

to segment the background/foreground of an image using imperative method

``` shell
graphg >> PGMALT /origin_source /destiny_source
```

image background/foreground segmentation demo - runs the two methods above in an example image

``` shell
graphg >> PGMDEMO
```