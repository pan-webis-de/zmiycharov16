package main;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import featureHelpers.*;

public class Errors {
	public static double RankingsMAP = 0;
	public static double ClustersFScore = 0;

	public static void calculateError() {
		calculateRankingsMAP();
		calculateClustersFScore();
	}
	
	// Calculate Mean Average Precision for rankings
	private static void calculateRankingsMAP() {
		double result = 0;
		
		for(JsonProblem problem : Globals.JsonProblems) {
			String folderName = problem.getFolder();
			
			double resultForFolder = 0;
			
			List<DocumentsSimilarity> folderRankings = Results.JsonRankings.get(folderName);
			
			double relevantDocsUntilNow = 0;
			
			for(int i = 0; i < folderRankings.size(); i++) {
				if(Globals.existsTrainSimilarity(folderName, folderRankings.get(i).getDocument1(), folderRankings.get(i).getDocument2())) {
					relevantDocsUntilNow++;
					
					resultForFolder += relevantDocsUntilNow / (i+1);
				}
			}
			
			result += resultForFolder / Globals.TrainSimilarities.get(folderName).size();
		}
		
		RankingsMAP = result / Globals.JsonProblems.size();
	}
	
	private static void calculateClustersFScore() {
		
	}
}