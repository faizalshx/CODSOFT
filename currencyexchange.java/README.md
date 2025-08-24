# 💱 Currency Converter (Java)

This is my **Task 1** for the CodSoft Internship (Java Programming).  
It is a simple **Currency Converter** that converts any amount from one currency to another using **live exchange rates** from an API.

---

## 📌 Features
- Select **base currency** and **target currency** from a predefined list  
- Enter any amount and get the **real-time conversion**  
- Works for multiple popular currencies:  
  - USD, EUR, INR, GBP, JPY, AUD, CAD, CNY  
- Live exchange rates fetched from the **Frankfurter API**  
- Accurate conversion for any amount entered  

---

## 🛠️ Tech Stack
- **Java**  
- **HttpURLConnection** → For API requests  
- **Gson** → For JSON parsing  
- **DecimalFormat** → For cleaner output  

---

## 🚀 How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/YOUR-USERNAME/CODSOFT.git
Open the project in VS Code or any Java IDE.

Download the gson-2.10.1.jar file and place it inside the lib folder.

Add the JAR file to the project’s build path.

Compile and run:

bash
Copy
Edit
javac -cp "lib/gson-2.10.1.jar" src/CurrencyConverter.java
java -cp "lib/gson-2.10.1.jar;src" CurrencyConverter
📷 Example Output
markdown
Copy
Edit
Available Currencies:
1. USD
2. EUR
3. INR
4. GBP
5. JPY
6. AUD
7. CAD
8. CNY

Choose base currency (1-8): 1
Choose target currency (1-8): 3
Enter amount: 5

Live Rate: 1 USD = 87.68 INR
5 USD = 438.40 INR
📌 Internship
This project is part of my CodSoft Java Programming Internship (Task 1).