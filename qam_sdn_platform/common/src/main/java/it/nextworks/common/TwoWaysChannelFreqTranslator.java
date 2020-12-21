package it.nextworks.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoWaysChannelFreqTranslator {
    private final double LIGHT_SPEED_MS = 299792458;
    private static final Logger LOG = LoggerFactory.getLogger(TwoWaysChannelFreqTranslator.class);
    private BiMap<Integer, Double> channelFreqBimap;

    public TwoWaysChannelFreqTranslator(final String filenameLookupTable) {
        channelFreqBimap = HashBiMap.create();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filenameLookupTable));
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            Set<String> objectSet = jsonObject.keySet();
            Iterator<String> itr = objectSet.iterator();
            while (itr.hasNext()) {
                String key = itr.next();
                String value = (String) jsonObject.get(key);

                Double waveLengthNanoMeter = Double.valueOf(key);
                Integer channel = Integer.valueOf(value);
                Double frequencyGhz = (LIGHT_SPEED_MS / waveLengthNanoMeter)  ;
                channelFreqBimap.put(channel, frequencyGhz);

            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            LOG.error(e.getMessage());
        }
    }


    public Set<Integer> getChannelSet() {
        return channelFreqBimap.keySet();
    }

    public Double channelToFrequency(int channelNumber) {
        return channelFreqBimap.get(channelNumber);
    }

    public Double getWavelengthNanometer(Double frequencyGhz){
        return 299792458/frequencyGhz;
    }

    public int frequencyToChannel(Double frequency) {
        if (channelFreqBimap.get(frequency) == null) {
            Set<Double> frequencyMhzSet = channelFreqBimap.values();

            Iterator<Double> itr = frequencyMhzSet.iterator();
            Double minDiff = Double.MAX_VALUE;
            Integer candidateChannel = -1;
            while (itr.hasNext()) {
                Double freqKey = itr.next();
                Integer channel = channelFreqBimap.inverse().get(freqKey);

                Double absDifferenceFreq = Math.abs(frequency - freqKey);
                if (absDifferenceFreq < minDiff) {
                    minDiff = absDifferenceFreq;
                    candidateChannel = channel;
                }
            }
            LOG.warn("Not able to find the exact channel for frequency " + frequency + " Ghz. Taken the channel " + candidateChannel);
            LOG.warn("Getting the channel " + candidateChannel + " with absolute difference of " + minDiff + " Ghz");
            return candidateChannel;
        }

        return channelFreqBimap.inverse().get(frequency);
    }
}
