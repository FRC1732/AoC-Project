package aoc.project.year2023.day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;
import org.checkerframework.checker.units.qual.s;

public class Day20Puzzle {
    private HashMap<String, RadioModule> radioMap;
    private ArrayList<Signal> signalQue;
    private int lowSingals;
    private int highSignals;
    private boolean rxReceivedSignal;

    public static void main(String[] args) {
        Day20Puzzle puzzle = new Day20Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2023, 20);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 20 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day20Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 20 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day20Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public boolean inList(String[] search, String label) {
        for (String element : search) {
            if (element.equals(label)) {
                return true;
            }
        }

        return false;
    }

    record Signal(RadioModule sender, String[] toSend, boolean high) {

    }

    public void queSignal(Signal signal) {
        signalQue.add(signal);
    }

    public void sendSignal(Signal signal) {
        RadioModule sender = signal.sender;
        String[] toSend = signal.toSend();
        boolean high = signal.high();
        // System.out.println(high + "-" + toSend.length);
        for (String signalKey : toSend) {
            // System.out.println(sender.getLabel() + " -(" + high + ")-> " + signalKey);
            if (signalKey.equals("rx") && !high) {
                rxReceivedSignal = true;
            }
            if (radioMap.containsKey(signalKey)) {
                radioMap.get(signalKey).recieveSignal(high, sender);
            }
        }

        if (high) {
            highSignals += toSend.length;
        } else {
            lowSingals += toSend.length;
        }
    }

    class RadioModule {
        protected String[] sendTo;
        protected String label;

        public RadioModule(String[] sendTo, String label) {
            this.sendTo = sendTo;
            this.label = label;
        }

        public void recieveSignal(boolean high, RadioModule from) {

        }

        public String[] getSendTo() {
            return sendTo;
        }

        public String getLabel() {
            return label;
        }

        public void reset() {
            
        }
    }

    class FlipflopModule extends RadioModule {
        private boolean charged;

        public FlipflopModule(String[] sendTo, String label) {
            super(sendTo, label);
            charged = false;
        }

        @Override
        public void recieveSignal(boolean high, RadioModule from) {
            if (high) {
                return;
            }

            charged = !charged;
            queSignal(new Signal(this, sendTo, charged));
        }
    }

    class Conjunction extends RadioModule {
        private HashMap<RadioModule, Boolean> inputs;

        public Conjunction(String[] sendTo, String label) {
            super(sendTo, label);
            inputs = new HashMap<>();
        }

        public void fetchInputs() {
            for (Entry<String, RadioModule> entry : radioMap.entrySet()) {
                RadioModule module = entry.getValue();
                if (inList(module.getSendTo(), label)) {
                    inputs.put(module, false);
                }
            }
        }

        public ArrayList<RadioModule> getInputs() {
            ArrayList<RadioModule> returnInputs = new ArrayList<>();
            for (Entry<RadioModule, Boolean> entry : inputs.entrySet()) {
                returnInputs.add(entry.getKey());
            }
            return returnInputs;
        }

        @Override
        public void recieveSignal(boolean high, RadioModule from) {
            inputs.put(from, high);

            boolean allHigh = true;
            for (Entry<RadioModule, Boolean> entry : inputs.entrySet()) {
                // System.out.println(label + ": " + entry.getKey().getLabel() + "-" +
                // entry.getValue());
                if (!entry.getValue()) {
                    allHigh = false;
                    break;
                }
            }
            // System.out.println();

            queSignal(new Signal(this, sendTo, !allHigh));
        }
    }

    class Broadcaster extends RadioModule {

        public Broadcaster(String[] sendTo, String label) {
            super(sendTo, label);
        }

        @Override
        public void recieveSignal(boolean high, RadioModule from) {
            queSignal(new Signal(this, sendTo, high));
        }
    }

    public long doPart1(List<String> lines) {
        // Part 1 code goes here
        radioMap = new HashMap<>();
        signalQue = new ArrayList<>();
        lowSingals = 0;
        highSignals = 0;

        Broadcaster broadcaster = new Broadcaster(null, null); // placeholder values

        for (String module : lines) {
            RadioModule createdModule;

            String[] sendStrings = module.substring(module.indexOf(">") + 2, module.length()).split(", ");
            String label = module.substring(1, module.indexOf(" "));
            char typeChar = module.charAt(0);

            switch (typeChar) {
                case '%':
                    createdModule = new FlipflopModule(sendStrings, label);
                    break;

                case '&':
                    createdModule = new Conjunction(sendStrings, label);
                    break;

                case 'b':
                    label = "broadcaster";
                    Broadcaster createdCaster = new Broadcaster(sendStrings, label);
                    broadcaster = createdCaster;
                    createdModule = broadcaster;
                    break;

                default:
                    System.err.print("Invalid radio module created.");
                    createdModule = new RadioModule(sendStrings, null);
                    break;
            }
            radioMap.put(label, createdModule);
        }

        for (Entry<String, RadioModule> entry : radioMap.entrySet()) {
            if (entry.getValue().getClass().hashCode() == Conjunction.class.hashCode()) {
                Conjunction conjunctionModule = (Conjunction) entry.getValue();
                conjunctionModule.fetchInputs();
            }
        }

        String[] broadcastList = { broadcaster.getLabel() };
        RadioModule button = new RadioModule(broadcastList, "button");
        radioMap.put("button", button);

        for (int i = 0; i < 1000; i++) {
            queSignal(new Signal(button, broadcastList, false));
            // System.out.println("high: " + highSignals);
            // System.out.println("low: " + lowSingals);
            // System.out.println();

            // highSignals = 0;
            // lowSingals = 0;

            while (signalQue.size() > 0) {
                sendSignal(signalQue.remove(0));
            }
        }

        // System.out.println("high: " + highSignals);
        // System.out.println("low: " + lowSingals);
        return highSignals * lowSingals;
    }

    public void resetRadios() {
        for (Entry<String, RadioModule> entry : radioMap.entrySet()) {
            RadioModule module = entry.getValue();
            module.reset();
        }
    }

    public long bruteForceActivationTime(RadioModule radio, RadioModule button, String[] broadcastList) {
        if (radio.getClass().equals(Broadcaster.class)) {
            return 1;
        }
        long count = 0;

        boolean done = false;

        while (!done) {
            count++;
            queSignal(new Signal(button, broadcastList, false));

            while (signalQue.size() > 0) {
                Signal toSend = signalQue.remove(0);
                sendSignal(toSend);
                if (inList(toSend.toSend(), radio.getLabel())) {
                    done = true;
                    break;
                }
            }
        }
        resetRadios();

        return count;
    }

    public long recurseRadio(Conjunction toCheck, RadioModule button, String[] broadcastList) {
        long multiple = 1;
        for (RadioModule radio : toCheck.getInputs()) {
            if (radio.getClass().equals(Conjunction.class)) {
                multiple *= recurseRadio((Conjunction) radio, button, broadcastList);
            } else {
                multiple *= bruteForceActivationTime(radio, button, broadcastList);
            }
        }
        return multiple;
    }

    public long doPart2(List<String> lines) {
        // Part 1 code goes here
        radioMap = new HashMap<>();
        signalQue = new ArrayList<>();
        lowSingals = 0;
        highSignals = 0;

        Broadcaster broadcaster = new Broadcaster(null, null); // placeholder values

        for (String module : lines) {
            RadioModule createdModule;

            String[] sendStrings = module.substring(module.indexOf(">") + 2, module.length()).split(", ");
            String label = module.substring(1, module.indexOf(" "));
            char typeChar = module.charAt(0);

            switch (typeChar) {
                case '%':
                    createdModule = new FlipflopModule(sendStrings, label);
                    break;

                case '&':
                    createdModule = new Conjunction(sendStrings, label);
                    break;

                case 'b':
                    label = "broadcaster";
                    Broadcaster createdCaster = new Broadcaster(sendStrings, label);
                    broadcaster = createdCaster;
                    createdModule = broadcaster;
                    break;

                default:
                    System.err.print("Invalid radio module created.");
                    createdModule = new RadioModule(sendStrings, null);
                    break;
            }

            radioMap.put(label, createdModule);
        }

        String[] broadcastList = { broadcaster.getLabel() };
        RadioModule button = new RadioModule(broadcastList, "button");
        radioMap.put("button", button);

        Conjunction finalConjuction = new Conjunction(broadcastList, null); // placeholder
        for (Entry<String, RadioModule> entry : radioMap.entrySet()) {
            if (entry.getValue().getClass().hashCode() == Conjunction.class.hashCode()) {
                Conjunction conjunctionModule = (Conjunction) entry.getValue();
                conjunctionModule.fetchInputs();

                if (inList(conjunctionModule.getSendTo(), "rx")) {
                    finalConjuction = conjunctionModule;
                }
            }
        }

        // System.out.println("high: " + highSignals);
        // System.out.println("low: " + lowSingals);
        return recurseRadio(finalConjuction, button, broadcastList);
    }

}
