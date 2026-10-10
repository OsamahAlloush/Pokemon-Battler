# Pokemon Battler CLI 🎮

A Java-based Command Line Interface (CLI) Pokemon game. Manage your Pokedex, save/load data, and battle wild Pokemons with a fully implemented turn-based battle engine.

## ✨ Features
- **Pokedex Management:** Add, edit, delete, and view Pokemon stats (tracking wins/losses).
- **Advanced Battle Engine:** Turn-based combat featuring **Critical Hits** (20% chance) and **Type Effectiveness** multipliers.
- **Polymorphism:** Fully polymorphic `Fighter` system (`HumanPlayer`, `CpuPlayer`) and `Attack` hierarchy (`DamageAttack` and `HealAttack`).
- **Data Persistence (JSON):** Save and load Pokedex data using the **Jackson** library, with complete annotation support for polymorphic object serialization/deserialization.
- **Wild Encounters:** Battle against a randomized pool of wild Pokemons.
- **Auto-healing:** Pokemons are automatically fully restored after every battle.
- **Crash-proof & Robust:** Full validation and exception handling to prevent crashes from empty Pokedex selections, fainted Pokemons, or missing attacks.

## 🛠️ Technologies Used
- **Java:** Core logic, Object-Oriented Programming (OOP) concepts.
- **Maven:** Project and dependency management.
- **Jackson (FasterXML):** For reading and writing structured JSON data.