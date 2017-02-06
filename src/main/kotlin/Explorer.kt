import Direction.*
import MazeSpace.WALL

enum class Direction {NORTH, EAST, SOUTH, WEST;

    fun left(): Direction = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }

    fun right(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}

private val directions = mapOf(NORTH to Point(0, -1), EAST to Point(1, 0), SOUTH to Point(0, 1), WEST to Point(-1, 0))

data class Explorer(val maze: Maze) {
    var position: Point
    var direction: Direction

    init {
        position = maze.start
        direction = NORTH
    }

    fun explore(): List<Point> {
        val route = arrayListOf(position)
        while (position != maze.end) {
            when {
                inDirection(direction.left()) != WALL -> turnLeft()
                inFront() != WALL -> { }
                inDirection(direction.right()) != WALL -> turnRight()
                else -> { turnLeft();turnLeft(); }
            }
            if(position + directions[direction]!! in route){
                route.remove(position)
            }
            moveForward()
            route.add(position)
        }
        return route
    }

    fun moveForward() {
        if (inFront() != WALL) position += directions[direction]!!
    }

    fun turnLeft() { direction = direction.left() }

    fun turnRight() { direction = direction.right() }

    fun inDirection(dir : Direction) : MazeSpace = maze[position + directions[dir]!!]

    fun adjacentSpaces(): List<Pair<Point,Direction>> = directions.map { Pair(position + it.value, it.key) }.filter { maze[it.first] != WALL }

    fun inFront(): MazeSpace = inDirection(direction)

}

fun main(args: Array<String>) {
    val maze = buildMaze(
            "XXXXXX\n" +
            "XS   X\n" +
            "XXX XX\n" +
            "XF  XX\n" +
            "XXXXXX")
    val explorer = Explorer(maze)
    val route = explorer.explore()
    println(route)
}