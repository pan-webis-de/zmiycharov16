package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import featureHelpers.DocumentsSimilarity;
import featureHelpers.Feature;
import features.MeanSentenceLength;
import features.Test1_Feature;
import features.Test2_Feature;
import features.Train_Feature;

public class Globals {
	public static List<Feature> Features;
	
	public static Map<String, List<File>> DocFiles;
	public static Map<String, List<DocumentsSimilarity>> TrainSimilarities; 

	public static Map<String, Double> FeaturesWeights;
	
	public static void init() {
		DocFiles = new HashMap<String, List<File>>();
		TrainSimilarities = new HashMap<String, List<DocumentsSimilarity>>(); 
		FeaturesWeights = new HashMap<String, Double>();
		
		Features = new ArrayList<Feature>();
//		Features.add(new Test1_Feature());
//		Features.add(new Test2_Feature());
		Features.add(new MeanSentenceLength("MeanSentenceLengthFeature"));
		if(Config.isTrainMode) {
//			Features.add(new Train_Feature());
		}
	}
}
