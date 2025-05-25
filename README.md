# 🌙 SeerApp

Seer is a console-based journaling tool designed to help users track their **mood**, **energy**, and **tasks** each day.  
Built in Java with a focus on clean code, modular design, and documentation.

## ✨ Features

- 📅 Log entries by date
- 🧠 Track your **mood** (type + level)
- ⚡ Log your **energy** (1–10 + notes)
- ✅ Record your **tasks** (name + status)
- 📦 Uses inheritance and polymorphism with `LogEntry` and subclasses
- 📄 JavaDocs included for all core classes

## 🛠 How to Use

1. Compile and run `StartApplication.java`
2. Follow the menu to:

```bash
> java StartApplication
Welcome to Seer
Choose an option:
1. Add log
2. View logs
3. Analyze patterns
4. Save logs to file
5. Load logs from file
6. Exit
```

## 📖 JavaDoc

To generate JavaDocs in IntelliJ:

1. Go to **Tools > Generate JavaDoc...**
2. Choose your output directory (e.g., `docs/`)
3. Click **OK**

Java will generate HTML documentation for your classes and methods, including descriptions and parameter info from your `/** JavaDoc comments */`.

## 📌 Notes

* The app uses a **single shared Scanner** instance to prevent input issues.
* Logs are stored in a `Map<String, List<LogEntry>>` where the key is the date.
* MoodLog, EnergyLog, and TaskLog inherit from a base class called `LogEntry`.

## 📅 Log Types

* **MoodLog** — stores mood type (e.g. "Happy") and level (1–10)
* **EnergyLog** — stores energy level and an optional note
* **TaskLog** — stores a task name and its status (e.g., "Done", "Pending")

## 🚧 Future Features

* View logs by date
* Analyze mood/energy patterns
* Save logs to a file
* Load logs from a file
* Optional: Graphs, reminders, export to calendar

## 🧑‍💻 Author

Crafted with ☕ and determination by a rising developer 🌟  

