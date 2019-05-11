package com.ram.systemtest.manager;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class DataProcessManager {

    public static final String SPLIT_REGEX = "[ \t\n]";

    public Observable<String> processTrueCaller10thCharacterResult(String data) {
        return Observable.just(processTrueCaller10thCharacter(data));
    }

    public Observable<String> processTrueCallerEvery10thCharacterResult(String data) {
        return Observable.just(processTrueCallerEvery10thCharacter(data));
    }


    public Observable<String> processTrueCallerWordCounterResult(String data) {
        return Observable.just(getWordCounterList(data));
    }

    private String processTrueCaller10thCharacter(String result) {
        return String.valueOf(result.charAt(9));
    }

    private String processTrueCallerEvery10thCharacter(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = s.length();
        int i = 9;
        while (i < length) {
            stringBuilder.append(s.charAt(i)).append("       ");
            i = i + 10;
        }
        return stringBuilder.toString();
    }

    private String getWordCounterList(String s) {
        String[] tokens = s.split(SPLIT_REGEX);
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Integer> hashMap =
                new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String res :
                tokens) {
            if (hashMap.containsKey(res)) {
                hashMap.put(res, (hashMap.get(res) + 1));
            } else {
                hashMap.put(res, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append("       ").append(entry.getValue()).append("\n");
        }

        return stringBuilder.toString();
    }
}
