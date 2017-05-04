package com.javarush.test.level34.lesson02.home01;

import java.lang.reflect.Method;


import java.util.ArrayList;
import java.util.List;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
//        solution.recursion("cos(30)", 0); //expected output 0.5 6
//        System.out.println();
//        System.out.println(solution.getBracesContent("sin(45(20+3)/(34+10))", 3));
//        System.out.println(solution.getOperationResult(" 2 / 3 ", 3));
//        System.out.println(solution.getLeftOperand("(-5) + -5.38 + 4", 13, solution.getLeftBound("(-5) + -5.38 + 4", 13)));
//        System.out.println(solution.getLeftBound("(-5) * -5.38 + 4", 13));
//        System.out.println(solution.getRightBound("(-5) * -5.38 + 4", 13));
//        System.out.println(solution.getRightOperand("(-5) * -5.38 + 4", 13, solution.getRightBound("(-5) * -5.38 + 4", 13)));
//        System.out.println(solution.getOperationResult("(-5) + -5.38 + 4", 13, solution.getLeftBound("(-5) + -5.38 + 4", 13), solution.getRightBound("(-5) + -5.38 + 4", 13)));
//        solution.solveExpression("((-20+3)/(-4+2)*(1^2))", 0);
//        System.out.println(solution.calculatePriorityExpression("(-20+3+34+10*2)"));
//        System.out.println(solution.calculateExpression("(-20+3+34+10*2)"));
//        System.out.println(solution.getFuncName("sin(30)", 3));
//        solution.solveExpression("(2*(-5+1.5*4) + (0+90) * 28)", 0);
//        System.out.println(solution.getPriorityOperatorPosition("sin(2*(-5+1.5*4)+28)"));
//        solution.solveExpression("sin(2*(-5+1.5*4)+28 * cos(15 - 15))", 0);
//        System.out.println(solution.getPriorityOperatorPosition("sin(2*(-5+1.5*4) + cos(0+90) * sin28"));
//        System.out.println(solution.getFuncPosition("(2*(-5+1.5*4) + (0+90) * 28"));
//        System.out.println(solution.calculateExpression("35 -6 * 5"));
    }

    /*
    * Не хочет приниматься :-)
    * */
    public void recursion(final String expression, int countOperation) {
        //implement
        boolean b = expression.matches("-?((\\d+[.]?)?\\d+)");
        if (b) {
            double value = Double.parseDouble(expression);
            value = Math.round(value * 100);
            value = value / 100;
            if (countOperation < 0)
                countOperation = -countOperation;
            System.out.print(value + " " + countOperation);
            return;
        }
        if (!(countOperation < 0))
            countOperation = -getOperationsCount(expression);
        int priorityOperationPos = getPriorityOperatorPosition(expression);
        int globalOpeningBracePos;
        int globalClosingBracePos;
        String funcName = getFuncName(expression, priorityOperationPos);
        boolean isTrigFunc = false;
        String trigonomFunc = "";
        if (!funcName.equals("no")) {
            isTrigFunc = true;
            globalOpeningBracePos = priorityOperationPos + 3;
            globalClosingBracePos = getClosingBracePos(expression, globalOpeningBracePos);
            trigonomFunc = expression.substring(priorityOperationPos, globalClosingBracePos + 1);
            String argumentOfTrigFunc = trigonomFunc.substring(3);
            priorityOperationPos = getPriorityOperatorPosition(argumentOfTrigFunc) + globalOpeningBracePos;
        } else
            globalOpeningBracePos = getOpeningBrace(expression);


        if (priorityOperationPos != -1)

            if (globalOpeningBracePos != -1) {
                String braceContent = getBracesContent(expression, globalOpeningBracePos);
                int openBracePos;
                int nestingDepth = 0;
                while (braceContent.contains("(")) {
                    openBracePos = braceContent.indexOf('(');
                    braceContent = getBracesContent(braceContent, openBracePos);
                    nestingDepth++;
                }
                String value = calculatePriorityExpression(braceContent);
                String tempExpression;
                if (value.matches("-?((\\d+[.]?)?\\d+)")) {
                    if (isTrigFunc && nestingDepth == 0) {
                        value = trigonomFunc(funcName, value);
//                        countOperation++;
                        braceContent = funcName + "(" + braceContent + ")";
                    } else
                        braceContent = "(" + braceContent + ")";
                }
                tempExpression = replsceExprInBraces(expression, braceContent, value);
//                recursion(tempExpression, ++countOperation);
                recursion(tempExpression, countOperation);

            }

    }

    private String trigonomFunc(String methodName, String argument) {
        double arg = Double.parseDouble(argument);
        arg = Math.toRadians(arg);
        try {
            Method trigonom = Math.class.getDeclaredMethod(methodName, double.class);
            double value = (double) trigonom.invoke(Math.class, arg);
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    private String getBracesContent(String expression, int openBracePos) {
        String content = "";
        if (openBracePos != -1) {
            int closingBracePos = getClosingBracePos(expression, openBracePos);
            content = expression.substring(openBracePos + 1, closingBracePos);
        }
        return content;
    }

    private int getClosingBracePos(String expression, int openBracePos) {
        int braceCount = 1;
        int closeBracePos = 0;
        for (int i = openBracePos + 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(')
                braceCount++;
            if (c == ')')
                braceCount--;
            if (braceCount == 0) {
                closeBracePos = i;
                break;
            }
        }
        return closeBracePos;
    }

    private String getOperationResult(String expression, int operatorPosition, int leftBound, int rightBound) {
        double result = 0;
        double leftOperand = Double.parseDouble(getLeftOperand(expression, operatorPosition, leftBound));
        double rightOperand = Double.parseDouble(getRightOperand(expression, operatorPosition, rightBound));
        switch (expression.charAt(operatorPosition)) {
            case '^':
                result = Math.pow(leftOperand, rightOperand);
                break;
            case '*':
                result = leftOperand * rightOperand;
                break;
            case '/':
                result = leftOperand / rightOperand;
                break;
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
        }
        return String.valueOf(result);
    }

    private String getLeftOperand(String expression, int operatorPosition, int leftBound) {
        String leftOperand = expression.substring(leftBound, operatorPosition).trim();
        return leftOperand;
    }

    private String getRightOperand(String expression, int operatorPosition, int rightBound) {
        String rightOperand = expression.substring(operatorPosition + 1, rightBound + 1);
        return rightOperand;
    }

    private int getLeftBound(String expression, int position) {
        if (position == 0 && expression.charAt(position) == '-')
            return 0;
        int leftBound = 0;
        int pos = position - 1;
        char c = expression.charAt(pos);
        while (pos >= 0) {
            c = expression.charAt(pos);
            if (!Character.isDigit(c)) {
                if (c == ')')
                    return -1;
            } else break;
            pos--;
        }
        while (pos >= 0) {
            c = expression.charAt(pos);
            if ((Character.isDigit(c) || c == '.' || c == '-'))
                pos--;
            else break;
        }
        int pos1 = pos;
        while (pos1 >= 0) {
            c = expression.charAt(pos1);
            if (c == '+' || c == '-')
                break;
            if (c == ')' || c == '*' || c == '/' || c == '^') {
                char operator = expression.charAt(position);
                if (operator == '+' || operator == '-')
                    return -1;
                else
                    break;
            }
            pos1--;
        }
        leftBound = ++pos;
        return leftBound;
    }

    private int getRightBound(String expression, int position) {
        int pos = position + 1;
        char c = expression.charAt(pos);
        while (pos < expression.length()) {
            c = expression.charAt(pos);
            if (!Character.isDigit(c)) {
                if (c == '(' || Character.isLetter(c))
                    return -1;
                pos++;
            } else
                break;
        }
        c = expression.charAt(pos);
        if (c == '-')
            pos++;
        while (pos < expression.length()) {
            c = expression.charAt(pos);
            if (Character.isDigit(c) || c == '.')
                pos++;
            else
                break;
        }
        int pos1 = pos;
        while (pos1 < expression.length()) {
            c = expression.charAt(pos1);
            if (c == '+' || c == '-')
                break;
            if (c == '(' || c == '*' || c == '/' || c == '^') {
                char operator = expression.charAt(position);
                if (operator == '+' || operator == '-')
                    return -1;
                else
                    break;
            }
            pos1++;
        }
        return --pos;
    }

    private int getOperatorPosition(String expression, int startPos) {
        int operatorPos = -1;
        String operationString = "^*/+-";
        for (int i = startPos; i < expression.length(); i++) {
            String s = String.valueOf(expression.charAt(i));
            if (operationString.contains(s))
                operatorPos = i;
            int pos = operatorPos;
            char c;
            while (pos >= 0) {
                c = expression.charAt(pos);
                if (Character.isDigit(c) || c == ')')
                    return operatorPos;
                pos--;
            }
        }
        return operatorPos;
    }

    private int getPriorityOperatorPosition(String expression) {
        int position = getFuncPosition(expression);
        if (position != -1)
            return position;
        String operatorString = "^*/+-";
        char[][] operatorsByPriority = {{'^'}, {'*', '/'}, {'+', '-'}};
        char c = ' ';
        char opChar;
        boolean isFinded = false;
        for (int i = 0; i < operatorsByPriority.length; i++) {
            char[] ops = operatorsByPriority[i];
            for (int j = 0; j < expression.length(); j++) {
                c = expression.charAt(j);
                for (int k = 0; k < ops.length; k++) {
                    opChar = ops[k];
                    if (opChar == c) {
                        isFinded = true;
                        position = j;
                        break;
                    }
                }
                if (isFinded)
                    break;
            }
            if (isFinded)
                break;
        }

        if (c == '-')
            return getOperatorPosition(expression, 0);
        return position;
    }

    private int getFuncPosition(String expression) {
        String[] trigonomOperator = {"sin", "cos", "tan"};
        int funcPos = -1;
        for (int i = 0; i < trigonomOperator.length; i++) {
            int tempFuncPos = expression.toLowerCase().lastIndexOf(trigonomOperator[i]);
            if (tempFuncPos != -1) {
                if (funcPos < tempFuncPos) {
                    funcPos = tempFuncPos;
                }
            }
        }
        return funcPos;
    }

    private int getOpeningBrace(String expression) {
        return expression.indexOf('(');
    }

    private String getFuncName(String expression, int position) {
        if (position > expression.length() - 2) {
            return "no";
        } else {
            String funcName = "no";
            if (Character.isLetter(expression.charAt(position))) {
                return expression.substring(position, position + 3).toLowerCase();
            }
        }
        return "no";
    }

    private String calculateExpression(String subExpression) {
        String value = "";
        String tempBrace = new StringBuilder(subExpression).toString();
        int operatorPosition = getPriorityOperatorPosition(tempBrace);
        while (operatorPosition != -1) {
            int leftBound = getLeftBound(tempBrace, operatorPosition);
            int rightBound = getRightBound(tempBrace, operatorPosition);
            if (leftBound != -1 && rightBound != -1) {
                value = getOperationResult(tempBrace, operatorPosition, leftBound, rightBound);
                String toReplace = tempBrace.substring(leftBound, rightBound + 1);
                tempBrace = replsceExprInBraces(tempBrace, toReplace, value);
                if (tempBrace.trim().matches("-?((\\d+[.]?)?\\d+)"))
                    return tempBrace;
            }
            operatorPosition = getPriorityOperatorPosition(tempBrace);
        }
        return tempBrace;
    }

    private String calculatePriorityExpression(String subExpression) {
        if (subExpression.trim().matches("-?((\\d+[.]?)?\\d+)"))
            return subExpression;
        String value = "";
        String tempBrace = new StringBuilder(subExpression).toString();
        int operatorPosition = getPriorityOperatorPosition(tempBrace);
        while (operatorPosition != -1) {
            int leftBound = getLeftBound(tempBrace, operatorPosition);
            int rightBound = getRightBound(tempBrace, operatorPosition);
            if (leftBound != -1 && rightBound != -1) {
                value = getOperationResult(tempBrace, operatorPosition, leftBound, rightBound);
                String toReplace = tempBrace.substring(leftBound, rightBound + 1);
                tempBrace = replsceExprInBraces(tempBrace, toReplace, value);

                return tempBrace;
            }
            operatorPosition = getPriorityOperatorPosition(tempBrace);
        }
        return tempBrace;
    }

    public void solveExpression(String expression, int countOperation) {
        boolean b = expression.matches("-?((\\d+[.]?)?\\d+)");
        if (b) {
            double value = Double.parseDouble(expression);
            value = Math.round(value * 100);
            value = value / 100;
            System.out.print(value + " " + countOperation);
            return;
        }

//        char currentChar;


        int priorityOperationPos = getPriorityOperatorPosition(expression);
        int globalOpeningBracePos;
        int globalClosingBracePos;
        String funcName = getFuncName(expression, priorityOperationPos);
        boolean isTrigFunc = false;
        String trigonomFunc = "";
        if (!funcName.equals("no")) {
            isTrigFunc = true;
            globalOpeningBracePos = priorityOperationPos + 3;
            globalClosingBracePos = getClosingBracePos(expression, globalOpeningBracePos);
            trigonomFunc = expression.substring(priorityOperationPos, globalClosingBracePos + 1);
            String argumentOfTrigFunc = trigonomFunc.substring(3);
            priorityOperationPos = getPriorityOperatorPosition(argumentOfTrigFunc) + globalOpeningBracePos;
        } else
            globalOpeningBracePos = getOpeningBrace(expression);

//        int globalLeftBound;

        if (priorityOperationPos != -1)
//            globalLeftBound = getLeftBound(expression, priorityOperationPos);

            if (globalOpeningBracePos != -1) {
//            currentChar = expression.charAt(globalOpeningBracePos);
//            int closingBracePos = getClosingBracePos(expression, globalOpeningBracePos);
                String braceContent = getBracesContent(expression, globalOpeningBracePos);
                int openBracePos = 0;
                int nestingDepth = 0;
                while (braceContent.contains("(")) {
                    openBracePos = braceContent.indexOf('(');
                    braceContent = getBracesContent(braceContent, openBracePos);
                    nestingDepth++;
                }
                String value = calculatePriorityExpression(braceContent);
                String tempExpression;
                if (value.matches("-?((\\d+[.]?)?\\d+)")) {
                    if (isTrigFunc && nestingDepth == 0) {
                        value = trigonomFunc(funcName, value);
                        countOperation++;
                        braceContent = funcName + "(" + braceContent + ")";
                    } else
                        braceContent = "(" + braceContent + ")";
                }
                tempExpression = replsceExprInBraces(expression, braceContent, value);
                System.out.println(tempExpression);
                solveExpression(tempExpression, ++countOperation);

//                int operatorPosition = getOperatorPosition(braceContent, 0);
//                int leftBound = getLeftBound(braceContent, operatorPosition);
//                int rightBound = getRightBound(braceContent, operatorPosition);
//                String value = "";
//                if (leftBound != -1 && rightBound != -1) {
//                    value = getOperationResult(braceContent, operatorPosition, leftBound, rightBound);
//                    String tempExpression = replsceExprInBraces(expression, "(" + braceContent + ")", value);
//                    System.out.println(tempExpression);
//                    solveExpression(tempExpression, ++countOperation);
//                }
            }
    }

    private String replsceExprInBraces(String expression, String toReplace, String value) {
        StringBuilder sb = new StringBuilder();
//        if (value.startsWith("-"))
//            value = "(" + value + ")";
        int start = expression.indexOf(toReplace);
        sb.append(expression.substring(0, start));
        sb.append(value);
        int stop = start + toReplace.length();
        sb.append(expression.substring(stop));
        return sb.toString();
    }

    private int getOperationsCount(String expression) {
        int result = 0;
        char c;
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            if ("^*/+-".contains(String.valueOf(c)))
                result++;
            else if (Character.isLetter(c)) {
                result++;
                i += 2;
            }
        }
        return result;
    }

    public Solution1() {
        //don't delete
    }
}
