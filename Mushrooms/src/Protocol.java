import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by kacper on 2017-04-06.
 */
public class Protocol {

    public String setMushroomFields(String parameter, Mushroom mushroom, int id) throws IOException {
        String info;
        System.out.print("Client: " + id + "   " );
        Reader reader = new StringReader(parameter);
        char c;
        String castReader;

        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setClasification(mushroom.getClasification().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapShape(mushroom.getCapShape().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapSurface(mushroom.getCapSurface().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapColor(mushroom.getCapColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setBruises(mushroom.getBruises().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setOdor(mushroom.getOdor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillAttachment(mushroom.getGillAttachment().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillSpacing(mushroom.getGillSpacing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillSize(mushroom.getGillSize().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillColor(mushroom.getGillColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkShape(mushroom.getStalkShape().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkRoot(mushroom.getStalkRoot().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkSurfaceAboveRing(mushroom.getStalkSurfaceAboveRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkSurfaceBelowRing(mushroom.getStalkSurfaceBelowRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkColorAboveRing(mushroom.getStalkColorAboveRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkColorBelowRing(mushroom.getStalkColorBelowRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setVeilType(mushroom.getVeilType().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setVeilColor(mushroom.getVeilColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setRingNumber(mushroom.getRingNumber().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setRingType(mushroom.getRingType().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setSporePrintColor(mushroom.getSporePrintColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setPopulation(mushroom.getPopulation().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setHabitat(mushroom.getHabitat().valueOf(castReader));

        reader.close();

        info = "request for gill attachment for a given mushroom: "+mushroom.getGillAttachment();
        return info;
    }


    /**
     * Show message about closeing connection
     * @return news to display
     */
    public String showExit(){
        String exit = "Closing connection with server";
        return exit;
    }

    /**
     * Show guide for user
     * @return news to display
     */
    public String showHelp(){
        String help = "List of available commands: SETBINREQUEST, SETDECREQUEST, GETDECREQUEST, GETBINREQUEST, "
                + "DECIMAL_CONVERSION, BINARY_CONVERSION, HELP, EXIT, QUIT";
        return help;
    }
}

