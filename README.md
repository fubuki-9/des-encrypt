Sure, let's integrate the details you've provided into the documentation for a more comprehensive overview.

---

# COMP3260 Assignment 3 - Client/Server Encryption System

## Description
This project is a Java-based Client/Server system developed for COMP3260 Assignment 3. It encrypts data for secure transport over a network using DES (Data Encryption Standard) and decrypts it at the other end. The system uses TCP/IP for client-server communication. While most functionalities work as intended, some issues with decryption remain.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Known Issues](#known-issues)
- [Contributing](#contributing)
- [License](#license)

## Installation
To install and run this project locally, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/your-project.git
   ```

2. Navigate to the project directory:

   ```bash
   cd your-project
   ```

3. Compile the Java files:

   ```bash
   javac *.java
   ```

4. Start the server:

   ```bash
   java Server
   ```

5. In a new terminal window, start the client:

   ```bash
   java Client
   ```

## Usage
Once the project is installed and running, follow these steps to use it:

1. **Connect to the Server:**
   - Open the client application.
   - Enter the server's IP address and port number.
   - Click the "Connect" button to establish a connection with the server.

2. **Load a File:**
   - Click the "Load File" button to select a file from your local machine.
   - The file's content will be read and prepared for encryption.

3. **Encrypt and Send Data:**
   - The data is automatically encrypted using DES and sent to the server over the network.

4. **Receive and Decrypt Data:**
   - The server receives the encrypted data.
   - The server decrypts the data and displays the original content.

## Features
- **DES Encryption/Decryption:** Encrypt data using DES before sending it over the network and decrypt it upon receiving.
- **Client-Server Communication:** Communicate between client and server using TCP/IP.
- **Graphical User Interface (GUI):** User-friendly GUI for loading files, connecting to the server, and displaying data.

## Known Issues
- **Decryption Errors:** Some decrypted data does not match the original data due to issues with the decryption process.
  - This might be due to incorrect handling of padding or transmission errors. Further debugging is required to resolve this issue.

## Contributing
Contributions to this project are welcome! Follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "Description of your changes"
   ```
4. Push your changes to your fork:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request on GitHub.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Sample Code Explanation

#### Client.java (Partial)

```java
public Client() {
    // Set the layout for the main content pane
    getContentPane().setLayout(new BorderLayout(5, 5));

    // Set the title of the window
    setTitle("COMP3260 Assignment 3 - c3007764");

    // Add a scroll pane containing the text area to the center of the layout
    getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

    // Create a panel with a grid layout for the buttons
    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
    buttonPanel.add(connectButton);
    buttonPanel.add(loadFileButton);
    buttonPanel.add(decryptButton);

    // Add mouse listeners to the buttons
    ButtonClickListener ml = new ButtonClickListener(this);
    connectButton.addMouseListener(ml);
    loadFileButton.addMouseListener(ml);
    decryptButton.addMouseListener(ml);

    // Add the button panel to the bottom of the layout
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    // Pack the components within the window and set its size
    pack();
    setSize(600, 400);
    setVisible(true);
}
```

- **Client() Constructor:** Initializes the client GUI components.
  - **Layout and Title:** Sets up the main layout and title.
  - **Text Area:** Adds a scrollable text area to the center for displaying messages.
  - **Button Panel:** Creates and adds buttons for connecting, loading files, and decrypting data.
  - **Event Listeners:** Attaches mouse listeners to handle button clicks.
  - **Window Setup:** Packs the components and sets the window size.

### Server.java (Skeleton)

```java
public class Server {
    public static void main(String[] args) {
        // Server setup code here
    }
}
```

- **Server Class:** Contains the main method to run the server.
  - **Server Setup:** Placeholder for server initialization and setup code.

---

Feel free to update the code snippets and descriptions based on your specific implementation. This documentation template provides a structured approach to explaining your project on GitHub.
