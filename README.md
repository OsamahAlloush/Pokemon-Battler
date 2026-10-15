# Pokemon Battler CLI 🎮

A Java-based Command Line Interface (CLI) Pokemon game. Manage your Pokedex, save/load data, and battle wild Pokemons with a fully implemented turn-based battle engine.

## ✨ Features
- **Pokedex Management:** Add, edit, delete, and view Pokemon. Includes a graceful cancellation system (`0` to cancel) during creation to enhance UX.
- **Advanced Battle Engine:** Turn-based combat featuring **Critical Hits** (1/16 chance) and **Type Effectiveness** multipliers.
- **Game Statistics :** Advanced persistent statistics tracking Win Rate (%), Total Wins/Losses, Most Used Pokemon, and Most Used Attack (implemented efficiently using `HashMap` and `Map.merge()`).
- **Polymorphism:** Fully polymorphic `Fighter` system (`HumanPlayer`, `CpuPlayer`) and `Attack` hierarchy (`DamageAttack` and `HealAttack`).
- **Data Persistence (JSON):** Save and load Pokedex data using the **Jackson** library. Includes crash-prevention safeguards for corrupted or missing save files.
- **Wild Encounters:** Battle against a randomized pool of wild Pokemons with unpredictable CPU move selections.
- **Crash-proof & Robust:** Full input validation (`while-loops`, `try-catch`) to prevent application crashes from invalid strings, out-of-range numbers, or empty Pokedex selections. Auto-healing restores Pokemons fully after every battle.

## 🛠️ Technologies Used
- **Java:** Core logic, Object-Oriented Programming (OOP) concepts, and Collections (`ArrayList`, `HashMap`).
- **Maven:** Project and dependency management.
- **Jackson (FasterXML):** For reading and writing structured JSON data with complete annotation support for polymorphic object serialization.