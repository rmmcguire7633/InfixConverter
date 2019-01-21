/**
 * prefix
 * Author - n/a
 * Last modified - 01/18/2019
 * copyright info - n/a
 * */

import java.util.Scanner;

/**
 * Used to create the Stack type.
 **/
class charStack {

  private final int STACKSIZE=100;
  private int top;
  private char [] items;

  // TO DO: implement the stack methods, see slides and/or book

  /**
   * initialize the stack.
   * top is set to -1 to represent being empty.
   **/
  public charStack() {

    items = new char[STACKSIZE];
    top = -1;
  }

  /**
   * Checks to see if the stack is empty.
   * @return will return true if stack is empty.
   **/
  public boolean empty() {

    if(top == -1) {

      return true;
    } else {

      return false;
    }
  }

  /**
   * Pops the top character on the stack.
   * @return char - the last character put into the stack.
   **/
  public char pop() {

    if(empty()) {

      System.out.println("Stack underflow");
      System.exit(1);
    }

    return items[top--];
  }

  /**
   * Pushes the next character into the stack.
   * @param x - the character being pushed to the stack.
   **/
  public void push(char x) {

    if(top == STACKSIZE - 1) {

      System.out.println("Stack Overflow");
      System.exit(1);
    }

    items[++top] = x;
  }

  /**
   * Looks at the last character put into the stack.
   * @return char - the last character put into the stack.
   **/
  public char peek() {

    if(empty()) {

      System.out.println("Stack fnderflow");
      System.exit(1);
    }

    return items[top];
  }
}

/**
 * Used to convert an infix expression to prefix.
 **/
class prefix{

  /**
   * Checks to see if the char passed in is an operand.
   * @param symb - the char charter from the infix string.
   * @return boolean - true if the char is an operand.
   **/
  public static boolean isOperand(char symb){
    if(symb=='+' || symb=='-' || symb=='*'
        || symb=='/' || symb=='$'
        || symb=='(' || symb==')'){
      return false;
    }else{
      return true;
    }
  }

  /**
   * Converts the infix expression into prefix.
   * @param infix - the infix expression.
   * @return String - the infix to prefix translation.
   **/
  public static String infix_to_prefix(String infix){
    // TO DO: implement this method to convert and return
    // the prefix of the infix string
    // you should call the precedence() method

    // holds the chars of the string while iterating through it.
    StringBuffer translatedStringHolder = new StringBuffer();

    // stack that will hold operands.
    charStack operatorStack = new charStack();

    // prefix notation of the infix expression.
    String prefix;

    // holds the char value of the string while iterating through it.
    char ch;

    // Used to iterate though the String right to left.
    for (int i = infix.length() - 1; i >= 0; i--) {

      ch = infix.charAt(i);

      if (isOperand(ch)) {

        translatedStringHolder.insert(0, ch);
      } else if (ch == ')' || operatorStack.empty()) {

        operatorStack.push(ch);
      } else if (ch == '(') {

        while (operatorStack.peek() != ')') {

          translatedStringHolder.insert(0, operatorStack.pop());
        }

        operatorStack.pop();
      } else {

        while (!operatorStack.empty() && !(operatorStack.peek() == ')')
          && !precedence(ch, operatorStack.peek())) {

          translatedStringHolder.insert(0, operatorStack.pop());
        }

        operatorStack.push(ch);
      }
    }

    while (!operatorStack.empty()) {

      translatedStringHolder.insert(0, operatorStack.pop());
    }

    prefix = translatedStringHolder.toString();
    return prefix;
  }

  /**
   * Compares for precedence.
   * @param op1 - incoming operator.
   * @param op2 - operator on top of the stack.
   * @return boolean - true if the incoming operator is greater than the
   * operator on top of the stack.
   **/
  public static boolean precedence(char op1, char op2){

    // TO DO: complete this method to compare for precedence
    // op2 is the operator on top of stack, op1 is the incoming operator
    int opcode1, opcode2;
    /* opcode for + or - is 1*/
    /* opcode for * or / is 2 */
    /* opcode for $ is 3 */

    opcode1 = getCharValue(op1);
    opcode2 = getCharValue(op2);

    if (op2 != '(' || op2 != ')') {
      if (opcode1 >= opcode2) {
        return true;
      } else
        return false;
    } else
      return false;
  }

  /**
   * Checks the char from the stack and assigns it a value based in its precedence.
   * If the char is + or - the return value will be 1.
   * If the char is * or / the return value will be 2.
   * If the char is $ the return value will be 3.
   * @param value - the char from the stack.
   * @return int - the value assigned to the passed in char for use with precedence.
   **/
  public static int getCharValue (char value) {

    if (value == '-' || value == '+') {

      return 1;
    } else if (value == '*' || value == '/') {

      return 2;
    } else if (value == '^' || value == '$'){

      return 3;
    } else {

      return 0;
    }
  }

  public static void main(String args[]){
    String infix, preFix;
    System.out.println("Enter an infix string: ");

    Scanner sc = new Scanner(System.in);

    // TO DO: read the infix string from the keyboard

    infix = sc.nextLine();

    preFix = infix_to_prefix(infix); // method to convert

    System.out.println("The prefix is " + preFix);
  }
}