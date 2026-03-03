# OrangeHRMAutomation 🚀

## 📌 Overview
OrangeHRMAutomation is a Hybrid Test Automation Framework developed using Selenium WebDriver and TestNG for automating the OrangeHRM application.

The framework follows industry-standard design principles Including Page Object Model (POM), reusable ActionDriver utilities, Data-Driven Testing, and CI integration with Jenkins/GitHub.

---

## 🏗 Framework Architecture

- Page Object Model (POM) design pattern
- Centralized reusable ActionDriver (custom wrapper over Selenium)
- Explicit wait handling for stability
- Data-driven testing using TestNG DataProviders + Excel (Apache POI)
- Extent Reports for professional reporting
- Maven for dependency management
- Git-based version control workflow
- Jenkins CI integration for automated execution

---

## ⚙️ Tech Stack

| Category        | Tools & Technologies |
|----------------|----------------------|
| Automation     | Selenium WebDriver, TestNG |
| Programming    | Java |
| Build Tool     | Maven |
| CI/CD          | Jenkins |
| Reporting      | ExtentReports |
| Data Handling  | Apache POI (Excel) |
| Version Control| Git, GitHub |
| IDE            | IntelliJ IDEA |

---

## 📂 Project Structure
src
├── main
│ ├── java
│ │ ├── actiondriver
│ │ ├── pages
│ │ └── utilities
├── test
│ ├── java
│ │ ├── base
│ │ ├── tests
│ │ └── utilities
│ └── resources
│ ├── testdata
│ └── testng.xml


---

## ▶️ How to Run the Project

### Run via Maven
```bash
mvn clean test
