package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.reader.FlashCardMaker;
import cs3500.pa02.writer.WriteFiles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class QuesitonBankHandler
 */
class QuestionBankHandlerTest {


  ArrayList<FlashCard> listOfCards1;
  ArrayList<FlashCard> listOfCards2;

  FlashCard fc1;

  FlashCard fc2;
  FlashCard fc3;

  Path srFile;

  FlashCardMaker flashCardMaker;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {

    listOfCards1 = new ArrayList<>();
    listOfCards2 = new ArrayList<>();

    srFile = Path.of("Examples/Examples3/sampleQB.sr");

    fc1 = new FlashCard("- What is the longest river in Europe?",
        "The longest river is the Volga River.", "EASY");
    fc2 = new FlashCard("- Which country is famous for its tulips and windmills?",
        "- The Netherlands.", "HARD");
    fc3 = new FlashCard("- Which country is famous for its tulips and windmills?",
        "- The Netherlands.", "EASY");
    //og file is the same, except the second one is easy and first is hard

    listOfCards1.add(fc1);
    listOfCards1.add(fc2);

    listOfCards2.add(fc1);
    listOfCards2.add(fc3);

    flashCardMaker = new FlashCardMaker(srFile);
  }

  /**
   * test for method resetSR() given flashcards that change
   */
  @Test
  public void testResetSr() {
    WriteFiles fileWriter = new WriteFiles();
    try {
      QuestionBankHandler.resetSr(srFile, listOfCards1);
    } catch (Exception e) {
      fail();
    }

    assertTrue(srFile.toFile().exists());

    StringBuilder sbActual = new StringBuilder();
    StringBuilder sbExpected = new StringBuilder();

    try {
      sbActual = flashCardMaker.read();
    } catch (Exception e) {
      fail();
    }

    sbExpected.append("- What is the longest river in Europe?:::").append(
            "The longest river is the Volga River. ").append("&&EASY&&")
        .append("\n").append("- Which country is famous for its tulips and windmills?:::").append(
            "- The Netherlands. ").append("&&HARD&&").append("\n");

    assertEquals(sbExpected.toString(), sbActual.toString());

    try {
      Files.delete(srFile);

      StringBuilder correctedContent = new StringBuilder();
      correctedContent.append("- What is the longest river in Europe?:::").append(
              "The longest river is the Volga River. ").append("&&HARD&&")
          .append("\n").append("- Which country is famous for its tulips and windmills?:::").append(
              "- The Netherlands. ").append("&&EASY&&");

      fileWriter.writeToFile(srFile, correctedContent);
    } catch (Exception e) {
      fail();
    }

  }

  /**
   * test for method resetSR() given no flashcards that change
   */
  @Test
  public void testResetSrNoChange() {
    WriteFiles fileWriter = new WriteFiles();
    try {
      QuestionBankHandler.resetSr(srFile, listOfCards2);
    } catch (Exception e) {
      fail();
    }

    assertTrue(srFile.toFile().exists());

    StringBuilder sbActual = new StringBuilder();
    StringBuilder sbExpected = new StringBuilder();

    try {
      sbActual = flashCardMaker.read();
    } catch (Exception e) {
      fail();
    }

    sbExpected.append("- What is the longest river in Europe?:::").append(
            "The longest river is the Volga River. ").append("&&EASY&&")
        .append("\n").append("- Which country is famous for its tulips and windmills?:::").append(
            "- The Netherlands. ").append("&&EASY&&").append("\n");

    assertEquals(sbExpected.toString(), sbActual.toString());

    try {
      Files.delete(srFile);

      StringBuilder correctedContent = new StringBuilder();
      correctedContent.append("- What is the longest river in Europe?:::").append(
              "The longest river is the Volga River. ").append("&&HARD&&")
          .append("\n").append("- Which country is famous for its tulips and windmills?:::").append(
              "- The Netherlands. ").append("&&EASY&&");

      fileWriter.writeToFile(srFile, correctedContent);
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * tests for class shuffleCards()
   */
  @Test
  public void testShuffleCards() {
    WriteFiles fileWriter = new WriteFiles();
    try {
      QuestionBankHandler.shuffleCards(listOfCards1, srFile);
    } catch (Exception e) {
      fail();
    }

    assertTrue(srFile.toFile().exists());

    StringBuilder sbActual = new StringBuilder();

    try {
      sbActual = flashCardMaker.read();
    } catch (Exception e) {
      fail();
    }

    String[] arraysOfsbActual = sbActual.toString().split("\n");

    assertTrue(arraysOfsbActual[0].contains("HARD"));
    assertTrue(arraysOfsbActual[arraysOfsbActual.length - 1].contains("EASY"));

    try {
      Files.delete(srFile);

      StringBuilder correctedContent = new StringBuilder();
      correctedContent.append("- What is the longest river in Europe?:::").append(
              "The longest river is the Volga River. ").append("&&HARD&&")
          .append("\n").append("- Which country is famous for its tulips and windmills?:::").append(
              "- The Netherlands. ").append("&&EASY&&");

      fileWriter.writeToFile(srFile, correctedContent);
    } catch (Exception e) {
      fail();
    }
  }
}