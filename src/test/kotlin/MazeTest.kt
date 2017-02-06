import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by jbrumby on 02/02/2017.
 */


class MazeTest{

    @Test fun shouldCreateAMaze(){
        val maze = buildMaze(
                "XXXXXXX\n" +
                "XSXX FX\n" +
                "X X  XX\n" +
                "X XX XX\n" +
                "X    XX\n" +
                "XXXXXXX\n")
        assertNotNull(maze, "Maze should not be null")
    }

    @Test fun startFinishSetCorrectly(){
        val maze = buildMaze(
                "XXXX\n" +
                "XS X\n" +
                "X FX\n" +
                "XXXX\n")
        assertEquals(Point(1,1), maze.start, "Start not stored correctly")
        assertEquals(Point(2,2), maze.end, "End not stored correctly")
    }

    @Test fun shouldReturnPointType() {
        val maze = buildMaze(
                "XXXX\n" +
                "XS X\n" +
                "X FX\n" +
                "XXXX\n")
        assertEquals(MazeSpace.WALL, maze[Point(0,0)])
        assertEquals(MazeSpace.START, maze[Point(1,1)])
        assertEquals(MazeSpace.END, maze[Point(2,2)])
        assertEquals(MazeSpace.SPACE, maze[Point(1,2)])
    }
}