

import java.net.*;
import java.io.*;


public class Server {


  public int portNumber = 3050;

  String key = "";
  String IV = "";
  String text = "";

  int[] PC1 = new int[]{57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
  int[] PC2 = new int[]{14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};


  int[] shift = new int[]{1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

  int[] IP = new int[]{58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
  int[] IIP = new int[]{40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25};

  int[] E = new int[]{32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};

  int[][] S1 = new int[][]{{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}};
  int[][] S2 = new int[][]{{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}};
  int[][] S3 = new int[][]{{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}};
  int[][] S4 = new int[][]{{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}};
  int[][] S5 = new int[][]{{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}};
  int[][] S6 = new int[][]{{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}};
  int[][] S7 = new int[][]{{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}};
  int[][] S8 = new int[][]{{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}};

  int[] P = new int[]{16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};


  ServerSocket serverSocket;
  Socket clientSocket;
  PrintWriter toClient;
  BufferedReader fromClient;

  public boolean commandStore = false;



  public Server(int port, String filename){
    portNumber = port;
    loadFile(filename);
    //cypher = encryptString(text);
    runServer();
    }




  public void openSocket(){
    try {

      if (serverSocket == null){
        serverSocket = new ServerSocket(portNumber);
        }

      if (clientSocket == null){
        System.out.println("Waiting for a connection");
        clientSocket = serverSocket.accept();
        System.out.println("Found a connection");
        }

      if (toClient == null){
        toClient = new PrintWriter(clientSocket.getOutputStream(), true);
        }

      if (fromClient == null){
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

      }
    catch (IOException e) {
      System.out.println("The port " + portNumber + " is busy");
      closeProgram();
      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }



  public void closeSocket(){
    try {

      if (serverSocket != null){
        serverSocket.close();
        }

      if (clientSocket != null){
        clientSocket.close();
        }

      if (fromClient != null){
        fromClient.close();
        }

      if (toClient != null){
        toClient.close();
        }

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }





  public void runServer(){
    try {

      openSocket();


      boolean closeServer = false;
      while (!closeServer){
        try {

          String clientInput = fromClient.readLine();

          if (clientInput.equalsIgnoreCase("GET")){

            encryptString(text);

            /*
            // the GET command
            // return a random string to the client
            int randomLine = (int)(Math.random() * numAnimals);
            toClient.println("GET: OK\nA " + animalSounds[randomLine][0] + " says " + animalSounds[randomLine][1]);
            */
            }

          else if (clientInput.equalsIgnoreCase("END")){
            /*
            // the END command
            toClient.println("END: OK");
            closeServer = true;
            */
            }

          else {
            // an unknown command
            toClient.println("I don't understand");
            }

          }
        catch (IOException e) {
          System.out.println("The client connection was rejected or closed");
          closeServer = true;
          }
        }

      closeSocket();

      closeProgram();

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }




  public void loadFile(String filename){
    try {

      RandomAccessFile inputFile = new RandomAccessFile(filename,"r");
      String keyLine = inputFile.readLine();
      String ivLine = inputFile.readLine();
      String textLine = inputFile.readLine();

      inputFile.close();

      key = keyLine.substring(keyLine.indexOf("{")+1,keyLine.lastIndexOf("}"));
      IV = ivLine.substring(ivLine.indexOf("{")+1,ivLine.lastIndexOf("}"));
      text = textLine.substring(textLine.indexOf("{")+1,textLine.lastIndexOf("}"));

      System.out.println("Key:  " + key);
      System.out.println("IV:   " + IV);
      System.out.println("Text: " + text);

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }



  public int[][] generateKeys(){
    try {

      // create 64-bit key
      int[] key64 = expandKeyTo64();


      // perform key permutation PC-1
      int[] keyPC1 = permutateKey(key64);


      // split and calculate the 16 subkeys
      int[][] keys48 = calculateSubkeys(keyPC1);


      return keys48;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] expandKeyTo64(){
    try {

      // create 64-bit key
      int[] key64 = new int[64];
      int paddingVal = 0;
      int outPos = 0;
      for (int i=0;i<56;i++){
        key64[outPos] = Integer.parseInt("" + key.charAt(i));
        paddingVal++;
        outPos++;
        if (paddingVal == 8){
          paddingVal = 0;
          key64[outPos] = 0;
          outPos++;
          }
        }

      return key64;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] permutateKey(int[] key64){
    try {

      int[] keyPC1 = new int[56];
      for (int i=0;i<56;i++){
        keyPC1[i] = key64[PC1[i] - 1];
        }

      System.out.println("Key After PC1: " + keyPC1);

      return keyPC1;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[][] calculateSubkeys(int[] keyPC1){
    try {

      // split the key in half
      int[] cKey = new int[28];
      int[] dKey = new int[28];

      System.arraycopy(keyPC1,0,cKey,0,28);
      System.arraycopy(keyPC1,28,dKey,0,28);

      //System.out.println("Key cKey: " + cKey);
      //System.out.println("Key dKey: " + dKey);

      // calculate the 16 sub-keys
      int[][] cSubKeys = new int[16][28];
      for (int i=0;i<16;i++){
        if (i==0){
          int startPos = 28 - shift[i];
          for (int j=0;j<28;j++){
            cSubKeys[i][j] = cKey[startPos];
            startPos += shift[i];
            if (startPos >= 28){
              startPos -= 28;
              }
            }
          //System.out.println("cSubkey " + (i+1) + ": " + cSubKeys[i]);
          }
        else {
          int startPos = 28 - shift[i];
          for (int j=0;j<28;j++){
            cSubKeys[i][j] = cSubKeys[i-1][startPos];
            startPos += shift[i];
            if (startPos >= 28){
              startPos -= 28;
              }
            }
          //System.out.println("cSubkey " + (i+1) + ": " + cSubKeys[i]);
          }
        }

      int[][] dSubKeys = new int[16][28];
      for (int i=0;i<16;i++){
        if (i==0){
          int startPos = 28 - shift[i];
          for (int j=0;j<28;j++){
            dSubKeys[i][j] = dKey[startPos];
            startPos += shift[i];
            if (startPos >= 28){
              startPos -= 28;
              }
            }
          //System.out.println("dSubkey " + (i+1) + ": " + dSubKeys[i]);
          }
        else {
          int startPos = 28 - shift[i];
          for (int j=0;j<28;j++){
            dSubKeys[i][j] = dSubKeys[i-1][startPos];
            startPos += shift[i];
            if (startPos >= 28){
              startPos -= 28;
              }
            }
          //System.out.println("dSubkey " + (i+1) + ": " + dSubKeys[i]);
          }
        }


  
      int[][] keys48 = new int[16][48];

      for (int i=0;i<16;i++){
        for (int j=0;j<48;j++){
          int readPos = PC2[j] - 1;
          if (readPos < 28){
            keys48[i][j] = cSubKeys[i][readPos];
            }
          else {
            readPos -= 28;
            keys48[i][j] = dSubKeys[i][readPos];
            }
          }

        String currentKey = "";
        for (int z=0;z<48;z++){
          currentKey += keys48[i][z];
          }
        System.out.println("Generated Key " + (i+1) + ": " + currentKey);
        }


      return keys48;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public void encryptString(String plainText){
    try {

      int[][] keys48 = generateKeys();

      int numBlocks = (text.length() / 64);
      if (text.length() % 64 > 0){
        numBlocks++;
        }

      // perform the encryption on each 64-bit block of text
      for (int b=0;b<numBlocks;b++){

        int[] block = new int[64];
        int blockSize = getPlainTextBlock(b,block);
        int numSLoops = 4;

        if (blockSize < 64){
          numSLoops = (int)(blockSize/16);
          if (blockSize%16 > 0){
            numSLoops++;
            }
          }


        int[] plain = new int[block.length];
        for (int i=0;i<block.length;i++){
          plain[i] = block[i];
          }

        // encrypt and send each sLoop block of 16 bits
        for (int s=0;s<numSLoops;s++){

          // if this is the first block, XOR with the IV string
          if (s == 0){
            doXORWithIV(block);
            }
          //System.out.println("Current Block: " + block);


          int[] blockIP = performInitialPermutation(block);


          // split the block in half
          int[] lBlock = new int[32];
          int[] rBlock = new int[32];

          System.arraycopy(blockIP,0,lBlock,0,32);
          System.arraycopy(blockIP,32,rBlock,0,32);

          //System.out.println("Left Block: " + lBlock);
          //System.out.println("Right Block: " + rBlock);


          // expand the right block to 48 bits
          int[] rExpanded = expandBlock(rBlock);

          // perform each key on the block
          for (int k=0;k<16;k++){

            // XOR with the key
            int rXOR[] = doXORWithKey(rExpanded,keys48[k]);

            // Do Sbox Substitutions
            int[] rSBoxBit = doSBoxSubstitution(rXOR);

            // Permutate
            int[] rPerm = permutate(rSBoxBit);

            // XOR with the left half of the text
            int[] rXORl = doXORWithLeftText(rPerm,lBlock);

            // prepare the blocks for the next iteration, and repeat for the remaining 16 keys
            //String left = "";
            //String right = "";
            //for (int z=0;z<lBlock.length;z++){
            //  left += lBlock[z];
            //  right += rBlock[z];
            //  }
            //System.out.println("LEFT:" + left);
            //System.out.println("RIGHT:" + right);

            lBlock = rBlock;
            rBlock = rXORl;

            }

          // do the inverse permutation
          int[] iipBlock = performInversePermutation(lBlock,rBlock);

          // do the sLoop stuff
          int[] xor16 = sLoop(plain, block, iipBlock);

          // send the 16 bits to the client, and repeat the sLoop
          String output = "";
          for (int t=0;t<16;t++){
            output += xor16[t];
            }

          toClient.println(output);
          System.out.println("SENT:" + output);
          }


        }

      toClient.println("DONE");

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }


  public int getPlainTextBlock(int blockNumber, int[] block){
    try {

      int startPos = blockNumber*64;
      int endPos = startPos+64;
      if (endPos > text.length()){
        endPos = text.length();
        java.util.Arrays.fill(block,0);
        }

      for (int i=startPos,j=0;i<endPos;i++,j++){
        block[j] = Integer.parseInt("" + text.charAt(i));
        }

      return endPos-startPos;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return 0;
      }
    }



  public void doXORWithIV(int[] block){
    try {

      for (int v=0;v<64;v++){
        /*
        if (Integer.parseInt("" + IV.charAt(v)) == block[v]){
          block[v] = 0;
          }
        else {
          block[v] = 1;
          }
        */
        block[v] = Integer.parseInt("" + IV.charAt(v));
        }

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }



  public int[] performInitialPermutation(int[] block){
    try {


      int[] blockIP = new int[64];
      for (int i=0;i<64;i++){
        //System.out.println("IP:" + (IP[i] - 1));
        //System.out.println("block:" + block[IP[i] - 1]);
        blockIP[i] = block[IP[i] - 1];
        //System.out.println("blockIP:" + blockIP[i]);
        }
      //System.out.println("Block after IP: " + blockIP);

      return blockIP;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] expandBlock(int[] rBlock){
    try {

      int[] rExpanded = new int[48];
      for (int i=0;i<48;i++){
        rExpanded[i] = rBlock[E[i] - 1];
        }
      //System.out.println("Expanded Right Block: " + rExpanded);

      return rExpanded;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }


  public int[] doXORWithKey(int[] rExpanded, int[] keys48){
    try {

      int[] rXOR = new int[48];
      for (int i=0;i<48;i++){
        if (keys48[i] == rExpanded[i]){
          rXOR[i] = 0;
          }
        else {
          rXOR[i] = 1;
          }
        }
      //System.out.println("Right Block after XOR: " + rXOR);

      return rXOR;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] doSBoxSubstitution(int[] rXOR){
    try {

      // split XOR result into 6-bit blocks
      int[][] rSplit = new int[8][6];
      for (int i=0;i<8;i++){
        for (int j=0;j<6;j++){
          rSplit[i][j] = rXOR[i*6+j];
          }
        }

      // perform S-box substitution
      int[] rSBox = new int[8];
      for (int i=0;i<8;i++){
        int row = rSplit[i][0]*2 + rSplit[i][5];
        int column = rSplit[i][1]*8 + rSplit[i][2]*4 + rSplit[i][3]*2 + rSplit[i][4];
        //System.out.println(row + " " + column);
        if (i==0){
          rSBox[i] = S1[row][column];
          }
        else if (i==1){
          rSBox[i] = S2[row][column];
          }
        else if (i==2){
          rSBox[i] = S3[row][column];
          }
        else if (i==3){
          rSBox[i] = S4[row][column];
          }
        else if (i==4){
          rSBox[i] = S5[row][column];
          }
        else if (i==5){
          rSBox[i] = S6[row][column];
          }
        else if (i==6){
          rSBox[i] = S7[row][column];
          }
        else if (i==7){
          rSBox[i] = S8[row][column];
          }
        }

      // reduce sBox results into bits
      int[] rSBoxBit = new int[32];
      java.util.Arrays.fill(rSBoxBit,0);

      int boxNum = 0;
      boolean boxFinished = false;
      for (int i=0;i<32;i+=4){
        int boxNumber = rSBox[boxNum];

        if (boxNumber >= 8){
          rSBoxBit[i] = 1;
          boxNumber -= 8;
          }
        if (boxNumber >= 4){
          rSBoxBit[i+1] = 1;
          boxNumber -= 4;
          }
        if (boxNumber >= 2){
          rSBoxBit[i+2] = 1;
          boxNumber -= 2;
          }
        if (boxNumber >= 1){
          rSBoxBit[i+3] = 1;
          boxNumber -= 1;
          }

        if (boxFinished){
          boxNum++;
          boxFinished = false;
          }
        else {
          boxFinished = true;
          }
        }

      //System.out.println("Right Block after S-Boxing: " + rSBoxBit);

      return rSBoxBit;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }




  public int[] permutate(int[] rSBoxBit){
    try {

      int[] rPerm = new int[32];
      for (int i=0;i<32;i++){
        rPerm[i] = rSBoxBit[P[i] - 1];
        }
      //System.out.println("Permutated Right Block: " + rPerm);

      return rPerm;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] doXORWithLeftText(int[] rPerm, int[] lBlock){
    try {

      int[] rXORl = new int[32];
      for (int i=0;i<32;i++){
        if (rPerm[i] == lBlock[i]){
          rXORl[i] = 0;
          }
        else {
          rXORl[i] = 1;
          }
        }

      //System.out.println("Right XOR Left Block: " + rXORl);

      //String result = "";
      //for (int i=0;i<rXORl.length;i++){
      //  result += rXORl[i];
      //  }
      //System.out.println(result);

      return rXORl;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] performInversePermutation(int[] lBlock, int[] rBlock){
    try {

      int[] iipBlock = new int[64];

      for (int i=0;i<64;i++){
        int readNum = IIP[i] - 1;
        if (readNum < 32){
          iipBlock[i] = rBlock[readNum];
          }
        else {
          readNum -= 32;
          iipBlock[i] = lBlock[readNum];
          }
        }

      return iipBlock;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] sLoop(int[] plain, int[] block, int[] iipBlock){
    try {

      // grab left 16 sBits of cipherText
      int[] left16bits = new int[16];
      System.arraycopy(iipBlock,0,left16bits,0,16);

      // grab left 16 bits of plainText
      int[] left16plain = new int[16];
      System.arraycopy(plain,0,left16plain,0,16);

      // XOR the 2 16-bit blocks
      //String result = "";
      int[] xor16 = new int[16];
      for (int e=0;e<16;e++){
        if (left16bits[e] == left16plain[e]){
          xor16[e] = 0;
          //result += "0";
          }
        else {
          xor16[e] = 1;
          //result += "1";
          }
        }

      //System.out.println(result);


      // Printing out the plaintext vs ciphertext for this block
      String plainText = "";
      String cipherText = "";

      for (int i=0;i<16;i++){
        plainText += plain[i];
        cipherText += xor16[i];
        }

      System.out.println ("PLAINTEXT: " + plainText + " goes to CIPHERTEXT: " + cipherText);


      // shift the block 16 to the left, and add in the XOR 16 bits on the right
      String oldIV = IV;
      //IV = "";
      for(int e=0,f=16;f<64;e++,f++){
        //IV += oldIV.charAt(f);
        block[e] = block[f];
        plain[e] = plain[f]; // moves the plain text along as well, easier than moving it before each sLoop
        //block[e] = Integer.parseInt("" + oldIV.charAt(f));
        }

      for (int e=48,f=0;e<64;e++,f++){
        block[e] = xor16[f];
        //IV += xor16[f];
        }

      return xor16;

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }


  public void closeProgram(){
    try {

      System.exit(0);

      }
    catch (Exception e){
      System.out.println("ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }




  public static void main(String[] args){

    Server svr = new Server(Integer.parseInt(args[0]), args[1]);

    }


  }