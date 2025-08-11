
# Currency Converter (Java)

This is my **Task 1** for the CodSoft Internship (Java Programming).  
It is a simple **Currency Converter** that converts any amount from one currency to another using **live exchange rates** from an API.

---

## 📌 Features
- Select base currency and target currency from a predefined list  
- Enter any amount and get the **real-time conversion**  
- Works for multiple popular currencies (USD, EUR, INR, GBP, JPY, AUD, CAD, CNY)  
- Live exchange rates fetched from the **Frankfurter API**  
- Accurate conversion for any amount entered  

---

## 🛠️ Tech Stack
- **Java**
- **HttpURLConnection** for API requests  
- **Gson** for JSON parsing  
- **DecimalFormat** for cleaner output  

---

## 🚀 How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/YOUR-USERNAME/CODSOFT.git 

Open the project in VS Code or any Java IDE.

Download the gson-2.10.1.jar file and place it inside the lib folder.

Add the JAR file to the project’s build path:

In VS Code, right-click the JAR → Add to Project.

Compile and run: 

javac -cp "lib/gson-2.10.1.jar" src/CurrencyConverter.java
java -cp "lib/gson-2.10.1.jar;src" CurrencyConverter

