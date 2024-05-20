




import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;


public class Client extends JFrame{


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


  public int portNumber = 3050;

  public String serverAddress = "127.0.0.1";

  Socket serverSocket;

  PrintWriter toServer;
  BufferedReader fromServer;
  BufferedReader keyboardStream = new BufferedReader(new InputStreamReader(System.in));

  public boolean commandStore = false;


  JTextArea textArea = new JTextArea("");
  JButton loadFileButton = new JButton("Load File");
  JButton connectButton = new JButton("Connection Settings");
  JButton decryptButton = new JButton("Decrypt File From Server");




  public Client(){
    getContentPane().setLayout(new BorderLayout(5,5));
    setTitle("COMP3260 Assignment 3 - c3007764");

    getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new GridLayout(1,3,5,5));
    buttonPanel.add(connectButton);
    buttonPanel.add(loadFileButton);
    buttonPanel.add(decryptButton);

    ButtonClickListener ml = new ButtonClickListener(this);
    connectButton.addMouseListener(ml);
    loadFileButton.addMouseListener(ml);
    decryptButton.addMouseListener(ml);

    getContentPane().add(buttonPanel,BorderLayout.SOUTH);

    pack();
    setSize(600,400);
    show();
    }



  public Client(String host, int port, String filename){
    serverAddress = host;
    portNumber = port;
    loadFile(filename);
    runClient();
    }



  public void openSocket(){
    try {

      if (serverSocket == null){
        serverSocket = new Socket(serverAddress, portNumber);
        }

      if (toServer == null){
        toServer = new PrintWriter(serverSocket.getOutputStream(), true);
        }

      if (fromServer == null){
        fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        }

      }
    catch (UnknownHostException e) {
      textArea.append("\n" + "The host was unable to be contacted");
      closeProgram();
      }
    catch (IOException e) {
      textArea.append("\n" + "Could not open a connection to the host server");
      closeProgram();
      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [CNT] - " + e);
      e.printStackTrace();
      }
    }



  public void closeSocket(){
    try {

      if (serverSocket != null){
        serverSocket.close();
        }

      if (fromServer != null){
        fromServer.close();
        }

      if (toServer != null){
        toServer.close();
        }

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [CNT] - " + e);
      e.printStackTrace();
      }
    }



  public void runClient(){
    try {

      
      openSocket();


        toServer.println("GET");


            text = "";
            String serverResponce = fromServer.readLine();

            while (! serverResponce.equals("DONE")){
              textArea.append("\n" + "SERVER >> " + serverResponce);
              text += serverResponce;
              serverResponce = fromServer.readLine();
              }

            encryptString(text);


      
      


      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [CNT] - " + e);
      e.printStackTrace();
      }
    }



  public void loadFile(String filename){
    try {

      RandomAccessFile inputFile = new RandomAccessFile(filename,"r");
      String keyLine = inputFile.readLine();
      String ivLine = inputFile.readLine();

      inputFile.close();

      key = keyLine.substring(keyLine.indexOf("{")+1,keyLine.lastIndexOf("}"));
      IV = ivLine.substring(ivLine.indexOf("{")+1,ivLine.lastIndexOf("}"));
      text = "";

      textArea.append("\n" + "Key:  " + key);
      textArea.append("\n" + "IV:   " + IV);
      

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }



  public int[][] generateKeys(){
    try {

      
      int[] key64 = expandKeyTo64();


      
      int[] keyPC1 = permutateKey(key64);


      
      int[][] keys48 = calculateSubkeys(keyPC1);


      return keys48;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] expandKeyTo64(){
    try {

      
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
      textArea.append("\n" + "ERROR [SVR] - " + e);
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

      textArea.append("\n" + "Key After PC1: " + keyPC1);

      return keyPC1;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[][] calculateSubkeys(int[] keyPC1){
    try {

      
      int[] cKey = new int[28];
      int[] dKey = new int[28];

      System.arraycopy(keyPC1,0,cKey,0,28);
      System.arraycopy(keyPC1,28,dKey,0,28);

      
      

      
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
        textArea.append("\n" + "Generated Key " + (i+1) + ": " + currentKey);
        }

      return keys48;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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

        
        for (int s=0;s<numSLoops;s++){

          
          if (s == 0){
            doXORWithIV(block);
            }
          


          int[] blockIP = performInitialPermutation(block);


          
          int[] lBlock = new int[32];
          int[] rBlock = new int[32];

          System.arraycopy(blockIP,0,lBlock,0,32);
          System.arraycopy(blockIP,32,rBlock,0,32);

          
          


          
          int[] rExpanded = expandBlock(rBlock);

          
          for (int k=0;k<16;k++){

            
            int rXOR[] = doXORWithKey(rExpanded,keys48[k]);

            
            int[] rSBoxBit = doSBoxSubstitution(rXOR);

            
            int[] rPerm = permutate(rSBoxBit);

            
            int[] rXORl = doXORWithLeftText(rPerm,lBlock);

            
            
            
            
            
            
            
            
            

            lBlock = rBlock;
            rBlock = rXORl;

            }

          
          int[] iipBlock = performInversePermutation(lBlock,rBlock);

          
          int[] xor16 = sLoop(plain, block, iipBlock);

          
          String output = "";
          for (int t=0;t<16;t++){
            output += xor16[t];
            }

          textArea.append("\n" + "Decrypted: " + output);
          }


        }

      textArea.append("\n" + "DONE");

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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


      String plainText = "";
      for (int i=0;i<block.length;i++){
        plainText += block[i];
        }
      textArea.append("\n" + "CIPHERTEXT:" + plainText);

      return endPos-startPos;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return 0;
      }
    }



  public void doXORWithIV(int[] block){
    try {

      for (int v=0;v<64;v++){
       
        block[v] = Integer.parseInt("" + IV.charAt(v));
        }

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      }
    }



  public int[] performInitialPermutation(int[] block){
    try {

      int[] blockIP = new int[64];
      for (int i=0;i<64;i++){
        blockIP[i] = block[IP[i] - 1];
        }
      

      return blockIP;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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
      

      return rExpanded;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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
      

      return rXOR;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] doSBoxSubstitution(int[] rXOR){
    try {

      
      int[][] rSplit = new int[8][6];
      for (int i=0;i<8;i++){
        for (int j=0;j<6;j++){
          rSplit[i][j] = rXOR[i*6+j];
          }
        }

      
      int[] rSBox = new int[8];
      for (int i=0;i<8;i++){
        int row = rSplit[i][0]*2 + rSplit[i][5];
        int column = rSplit[i][1]*8 + rSplit[i][2]*4 + rSplit[i][3]*2 + rSplit[i][4];
        
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

      

      return rSBoxBit;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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
      

      return rPerm;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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

      
      
      
      
      

      

      return rXORl;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
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
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }



  public int[] sLoop(int[] plain, int[] block, int[] iipBlock){
    try {

      
      int[] left16bits = new int[16];
      System.arraycopy(iipBlock,0,left16bits,0,16);

      
      int[] left16plain = new int[16];
      System.arraycopy(plain,0,left16plain,0,16);

      
      
      int[] xor16 = new int[16];
      for (int e=0;e<16;e++){
        if (left16bits[e] == left16plain[e]){
          xor16[e] = 0;
          
          }
        else {
          xor16[e] = 1;
          
          }
        }

      


      
      String oldIV = IV;
      
      for(int e=0,f=16;f<64;e++,f++){
        
        block[e] = block[f];
        plain[e] = plain[f]; 
        
        }

      for (int e=48,f=0;e<64;e++,f++){
        block[e] = xor16[f];
        
        }

      return xor16;

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [SVR] - " + e);
      e.printStackTrace();
      return null;
      }
    }


  public void closeProgram(){
    try {

      if (serverSocket != null){
        serverSocket.close();
        }
      System.exit(0);

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [CNT] - " + e);
      e.printStackTrace();
      }
    }



  public void buttonClicked(JButton buttonObj){
    try {

      if (buttonObj == loadFileButton){
        JFileChooser fc = new JFileChooser(new File("").getAbsolutePath());
        fc.showOpenDialog(this);
        String filename = fc.getSelectedFile().getAbsolutePath();
        loadFile(filename);
        }
      else if (buttonObj == connectButton){
        serverAddress = JOptionPane.showInputDialog("Address of server",serverAddress);
        portNumber = Integer.parseInt(JOptionPane.showInputDialog("Port number for communication",portNumber + ""));
        }
      else if (buttonObj == decryptButton){
        runClient();
        }

      }
    catch (Exception e){
      textArea.append("\n" + "ERROR [CNT] - " + e);
      e.printStackTrace();
      }
    }




  public static void main(String[] args){

    if (args.length > 0){
      Client cnt = new Client(args[0],Integer.parseInt(args[1]),args[2]);
      }
    else {
      Client cnt = new Client();
      cnt.addWindowListener(new WindowClosingListener(cnt));
      }

    }


}
