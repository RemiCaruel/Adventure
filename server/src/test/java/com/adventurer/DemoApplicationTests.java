package com.adventurer;

import com.adventurer.logic.Adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void ElementCreationTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Element Creations:\n");
        System.out.print("Creating a mountain => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Element_Creation/Mountain_creation_test.txt"));
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Element_Creation/Mountain_creation_result.txt"));
        System.out.println("ok\n");
        System.out.print("Creating a treasure => ");
        newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Element_Creation/Treasure_creation_test.txt"));
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Element_Creation/Treasure_creation_result.txt"));
        System.out.println("ok");
    }

    @Test
    public void ElementCreationErrorTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Errorneous Element Creations:\n");
        System.out.print("Creating an errorneous mountain => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Element_Creation/Mountain_creation_error_test.txt"));
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Element_Creation/Mountain_creation_error_result.txt"));
        System.out.println("ok\n");
        System.out.print("Creating a treasure => ");
        newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Element_Creation/Treasure_creation_error_test.txt"));
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Element_Creation/Treasure_creation_error_result.txt"));
        System.out.println("ok");
    }

    @Test
    public void MapCreationTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Map Creation:\n");
        System.out.print("Creating a map => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Map_Creation/Map_Creation_test.txt"));
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Map_Creation/Map_Creation_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void MapCreationErrorTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Map Errorneous Creation:\n");
        System.out.print("Creating a map => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Map_Creation/Map_Creation_error_test.txt"));
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Map_Creation/Map_Creation_error_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void PlayerCreationTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Player Creation:\n");
        System.out.print("Creating a Player => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Player_Creation/payer_creation_test.txt"));
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Player_Creation/payer_creation_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void PlayerCreationErrorTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Player Errorneous Creation:\n");
        System.out.print("Creating a Player => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Player_Creation/payer_creation_error_test.txt"));
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Player_Creation/payer_creation_error_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void BlockedWayTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Player Blocked way :\n");
        System.out.print("Testing Player Path => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Blocked_way/blocked_way_test.txt"));
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Blocked_way/blocked_way_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void CommentManagement() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing Comment Management functionnality:\n");
        System.out.print("Reading file with comments => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Comment/Comments_test.txt"));
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Comment/Comments_result.txt"));
        System.out.println("ok\n");
    }

    @Test
    public void NonExistingCommandsTest() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("           Testing User Non existing commands possibilities:\n");
        System.out.print("Non Existing commands management => ");
        Adventure newAdventure = new Adventure("Test", this.readFromInputStream("testing_adventures/Non_existing_commands/Non_Existing_Commands_test.txt"));
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        newAdventure.step();
        Assert.assertEquals(newAdventure.getState(), this.readFromInputStream("testing_adventures/Non_existing_commands/Non_Existing_Commands_result.txt"));
        System.out.println("ok\n");
    }



    /**
     * Read a file and returns its content
     * @param path path of the file
     * @return content of the file
     * @throws IOException in case of file reading error
     */
    private String readFromInputStream(String path)
        throws IOException {
            InputStream inputStream = (new ClassPathResource(path)).getInputStream();
            StringBuilder resultStringBuilder = new StringBuilder();
            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
        return resultStringBuilder.toString();
    }

}
