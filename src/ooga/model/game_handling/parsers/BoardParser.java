package ooga.model.game_handling.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooga.display.tileviews.TileView;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.TileModel;


/**
 * Parser class responsible for parsing .board file
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */
public class BoardParser {

  public BoardParser(){
  }

  public List<TileModel> parseBoard(String boardFilePath, Map<String,TileModel> tileMap) throws IOException {
    List<TileModel> boardTileModels = new ArrayList<>();

    File file=new File(boardFilePath);
    FileReader fileReader =new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line;
    while((line = bufferedReader.readLine())!=null)
    {
      if(tileMap.containsKey(line)){
        boardTileModels.add(tileMap.get(line));
      }
      else{
        System.out.println("Couldn't find: " + line);
      }
    }

    return boardTileModels;
  }

}
