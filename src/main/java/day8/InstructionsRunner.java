package day8;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionsRunner {

    public static final int THRESHOLD = 100;
    public final  List<Instruction> instructions;


    public InstructionsRunner(List<String> instructions) {
        this.instructions = parseInstructions(instructions);
    }


    public int swapAndCount() {
        correctInstructions();
        int accSum = 0;
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.isAccumulator()) {
                accSum += instruction.getValue();
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
        }
        return accSum;
    }

    private void correctInstructions() {
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.isJump()) {
                System.out.println("1. instruction found as jmp: " + instruction + " - switching ");
                instruction.switchInstruction();
                System.out.println("changed: " + instruction);
                if (isInfinite(instructions)) {
                    instruction.switchInstruction();
                    continue;
                } else {
                    System.out.println("changed jmp: " + i);
                    break;
                }
            }


            if (instruction.isNoOpp()) {
                instruction.switchInstruction();
                System.out.println("changed: " + instruction);

                if (isInfinite(instructions)) {
                    instruction.switchInstruction();
                } else {
                    System.out.println("changed nop: " + i);
                    break;
                }
            }
        }
        System.out.println("returning instructions...");
    }

    private boolean isInfinite(List<Instruction> instructions) {
        resetExecutions(instructions);
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            instruction.execute();
            if (instruction.hasThresholdReached(THRESHOLD)) {
                System.out.println("is infinite...");
                return true;
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
        }
        System.out.println("*****************************************************************************************88");
        return false;
    }

    private void resetExecutions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            instruction.resetExecution();
        }
    }

    public int accumulatorValueSecondTimeInstruction(List<String> instructionLines) {
        List<Instruction> instructions = parseInstructions(instructionLines);
        int accSum = 0;
        for (int i = 0; i < instructions.size(); i++) {
            final Instruction instruction = instructions.get(i);
            if (instruction.hasBeenExecuted()) {
                return accSum;
            }
            if (instruction.isAccumulator()) {
                accSum += instruction.getValue();
            }
            if (instruction.isJump()) {
                i = i + instruction.getValue() - 1;
            }
            instruction.execute();
        }
        return accSum;
    }

    private List<Instruction> parseInstructions(List<String> instructions) {
        return instructions.stream()
                .map(s -> s.split(" "))
                .map(a -> new Instruction(InstructionType.valueOf(a[0]), Integer.parseInt(a[1])))
                .collect(Collectors.toList());
    }
}
