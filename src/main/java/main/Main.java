package main;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

	// To run from console: mvn exec:java -Dexec.args="-i $inputDataset -o $outputDir"
	public static void main(String[] args) throws Exception {
		// SET IsTrainMode
		Config.isTrainMode = !(new File(Config.TRAIN_FILE_PATH).exists());
		
		// INIT
		Globals.init();
		
		// SET FOLDERS
		Config.setFolders(args);

		// SETUP INPUT
		File inputFolder = new File(Config.inputFolderPath);
		File inputInfoJson = new File(inputFolder, "info.json");
		
		Type jsonProblemListType = new TypeToken<ArrayList<JsonProblem>>() {}.getType();
		Globals.JsonProblems = new Gson().fromJson(FileUtils.readFileToString(inputInfoJson), jsonProblemListType);
		
		for(JsonProblem problem : Globals.JsonProblems) {
			String folderName = problem.getFolder();

			FeaturesGenerator.generateDocFiles(inputFolder, folderName);
			
			if(Config.isTrainMode) {
				FeaturesGenerator.setActualSimilarities(folderName);
			}

			Date start = new Date();
			FeaturesGenerator.generateFeaturesSimilarities(inputFolder, folderName);
			
			Date now = new Date();
			
			long total = now.getTime() - start.getTime();
			
			long seconds = total/1000;
			long millis = total%1000;
			
			System.out.println("Generated features: " + folderName + " (" + seconds + "." + millis + " sec)");
		}
		
		// TRAIN
		Logistic.trainResults();

		// CLEAR OUTPUT FOLDER
		System.out.println("Generate output");
		File outputFolder = new File(Config.outputFolderPath);
		
		if(!outputFolder.exists()) {
			outputFolder.mkdir();
		}
		
		FileUtils.cleanDirectory(outputFolder);
		
		// GENERATE RESULTS
		for(JsonProblem problem : Globals.JsonProblems) {
			String folderName = problem.getFolder();
			
			Results.generateResults(folderName);
			
			Results.generateOutput(new File(outputFolder, folderName));
		}
		
		// CALCULATE ERROR ONLY IF TRAIN MODE
		if(Config.isTrainMode) {
			Errors.calculateError();
			
			System.out.println("Rankings MAP: " + Errors.RankingsMAP);
			System.out.println("Clusters F-score: " + Errors.ClustersFScore);
		}

		System.out.println("Finished!");
	}

}
