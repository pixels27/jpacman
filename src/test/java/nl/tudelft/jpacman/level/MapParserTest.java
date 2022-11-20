package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class for MapParser.
 * # Blinky is our ghost
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     * 'P' (capital P) a starting square for players.
     * 'G' (capital G) a square with a ghost.
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");
        mapParser.parseMap(map);
        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
    }

    /**
     * Test for the parseMap method (bad map).
     * checkMapFormat -> Check the correctness of the map lines in the text.
     * throws PacmanConfigurationException if map is not OK.
     */

    @Test
    public void testParseMapWrong1() {
        PacmanConfigurationException thrown =
            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
                MockitoAnnotations.initMocks(this);
                assertNotNull(boardFactory);
                assertNotNull(levelFactory);
                MapParser mapParser = new MapParser(levelFactory, boardFactory);
                ArrayList<String> map = new ArrayList<>();
                map.add("############");
                map.add("#p        G#");
                map.add("############");
                mapParser.parseMap(map);
            });
        Assertions.assertEquals("Invalid character at 1,1: p", thrown.getMessage());
    }

//    @Test
//    public void testParseMapWrong2() {
//        PacmanConfigurationException thrown =
//            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
//                MockitoAnnotations.initMocks(this);
//                assertNotNull(boardFactory);
//                assertNotNull(levelFactory);
//                MapParser mapParser = new MapParser(levelFactory, boardFactory);
//                ArrayList<String> map = new ArrayList<>();
//                mapParser.parseMap(map);
//            });
//        Assertions.assertEquals("Input text must consist of at least 1 row.", thrown.getMessage());
//    }
//
//    @Test
//    public void testParseMapWrong3() {
//        PacmanConfigurationException thrown =
//            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
//                MockitoAnnotations.initMocks(this);
//                assertNotNull(boardFactory);
//                assertNotNull(levelFactory);
//                MapParser mapParser = new MapParser(levelFactory, boardFactory);
//                ArrayList<String> map = new ArrayList<>();
//                map.add("");
//                mapParser.parseMap(map);
//            });
//        Assertions.assertEquals("Input text lines cannot be empty.", thrown.getMessage());
//    }
//
//    @Test
//    public void testParseMapWrong4() {
//        PacmanConfigurationException thrown =
//            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
//                MockitoAnnotations.initMocks(this);
//                assertNotNull(boardFactory);
//                assertNotNull(levelFactory);
//                MapParser mapParser = new MapParser(levelFactory, boardFactory);
//                ArrayList<String> map = new ArrayList<>();
//                map.add("#########");
//                map.add("#P        G#");
//                map.add("###############");
//                mapParser.parseMap(map);
//            });
//        Assertions.assertEquals("Input text lines are not of equal width.", thrown.getMessage());
//    }
}
