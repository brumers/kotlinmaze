import MazeSpace.*
import java.util.*

data class Point(val x: Int, val y: Int)

operator fun Point.plus(other : Point) : Point = Point(this.x + other.x, this.y + other.y)

enum class MazeSpace(val c: Char) {WALL('X'), START('S'), END('F'), SPACE(' ');

    companion object {fun fromChar(c : Char) :MazeSpace = values().first { it.c.equals(c, true) }}
}

data class Maze(val start: Point, val end: Point, val walls: List<Point>) {

    operator fun get(point: Point): MazeSpace =
            when (point) {
                start -> START
                end -> END
                in walls -> WALL
                else -> SPACE
            }
}

fun buildMaze(init: String): Maze {
    var start: Point? = null
    var end: Point? = null
    var walls: ArrayList<Point> = ArrayList()


    init.split("\n").forEachIndexed { row, s -> s.forEachIndexed { col, c ->
            val e = MazeSpace.fromChar(c)
            when (e) {
                WALL -> walls.add(Point(col, row))
                START -> start = Point(col, row)
                END -> end = Point(col, row)
                SPACE -> { }
                else -> throw IllegalArgumentException("Invalid value in the maze string: $c")
            }
        }
    }
    return Maze(start ?: throw IllegalArgumentException("Start point has not been defined"),
            end ?: throw IllegalArgumentException("Finish Point has not been defined"),
            walls.toList())
}