package ooga.model.game_handling.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import ooga.display.tileviews.TileView;
import ooga.model.data.properties.Property;


/**
 * Parser class responsible for parsing .board file
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */
public class BoardParser {

  private ArrayList<Property> propertyList;

  public BoardParser(ArrayList<Property> propList){
    propertyList = propList;
  }

  public ArrayList<TileView> parseBoard(String boardFilePath) throws IOException {
    ArrayList<String> boardElements = new ArrayList<>();
    File file=new File(boardFilePath);
    FileReader fileReader =new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line;
    while((line = bufferedReader.readLine())!=null)
    {
      boardElements.add(line);
      System.out.println(line);
    }

    return null;
  }

}