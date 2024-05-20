## Description
This project is a Java-based Client/Server system. It encrypts data for secure transport over a network using DES (Data Encryption Standard) and decrypts it at the other end. The system uses TCP/IP for client-server communication. While most functionalities work as intended, some issues with decryption remain.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Known Issues](#known-issues)
- [Contributing](#contributing)
- [License](#license)

## Installation
To install and run this project locally, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/fubuki-9/des-encrypt.git
   ```

2. Navigate to the project directory:

   ```bash
   cd des-encrypt
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
This project is licensed under the Apache 2 - see the [LICENSE](LICENSE) file for details.

---

