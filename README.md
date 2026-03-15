# Valley Simulation Game
### Object-Oriented Game Engine Project (Java)

A modular **turn-based simulation game** built using **Java** and the **Monash FIT2099 game engine**.

This project explores **object-oriented architecture, extensible gameplay systems, and SOLID design principles** through the implementation of dynamic NPC behaviours, environmental mechanics, and boss encounters.

The game places the player in the role of the **Farmer**, exploring the **Valley of the Inheritree**, interacting with creatures, planting crops, curing corruption, and confronting powerful entities.

---

# Project Highlights

- Behaviour-driven NPC system  
- Modular crop planting and environmental transformation  
- Countdown-based lifecycle systems  
- Dynamic creature reproduction mechanics  
- Extensible boss architecture  
- Teleportation across maps  
- Capability-based interaction system  

The project demonstrates strong object-oriented design, with emphasis on **extensibility and clean architecture**.

---

# Tech Stack

## Language
- Java

## Architecture
- Object-Oriented Design
- SOLID Principles
- Behaviour Composition

## Engine
- Monash FIT2099 Game Engine

## Key Concepts
- Strategy Pattern
- Interface-driven design
- Behaviour prioritisation
- Event-driven mechanics

---

# Game Overview

The game takes place on a **grid-based map** where actors, items, and terrain interact each turn.

The player can:

- Explore the valley
- Plant crops
- Interact with NPCs
- Cure environmental corruption
- Collect items
- Fight creatures and bosses

NPCs operate through a **behaviour system**, where each turn they evaluate a list of behaviours and execute the highest priority valid action.

Example behaviours include:

- Wandering
- Following actors
- Reproducing
- Attacking
- Producing items

This system allows new behaviours to be added **without modifying existing code**.

---

# My Contributions

My work focused primarily on **advanced gameplay mechanics and extensible architecture**, including:

- Golden Beetle ecosystem
- Golden Egg lifecycle system
- Countdown-based behaviour system
- BedOfChaos boss system
- Improvements to the NPC behaviour architecture

---

# Golden Beetle System

The **Golden Beetle** is a dynamic NPC that interacts with both the player and the environment.

It demonstrates **behaviour composition, lifecycle management, and environmental interaction**.

### Key Mechanics

## Egg Production

Every **five turns** the beetle produces a **Golden Egg**.

This is implemented using a reusable countdown system composed of:

- `Countdown`
- `CountdownBehaviour`
- `ProduceAction`

This design allows **any entity to trigger time-based events** without embedding logic directly inside the actor.

---

## Follow Behaviour

Golden Beetles can detect nearby actors with a **followable capability** and begin following them.

Once following begins, the beetle continues tracking the actor until either entity leaves the map.

---

## Player Interaction

If the player stands adjacent to a Golden Beetle, it can be **consumed instantly** without being picked up.

Consuming the beetle:

- Restores health
- Grants **1000 runes**

---

## Rot Immunity

Unlike other valley creatures, Golden Beetles are **immune to Crimson Rot** and therefore do not use decay countdown timers.

---

# Golden Egg Lifecycle

Golden Eggs extend the abstract **Egg system** used across multiple creature types.

They support two mutually exclusive behaviours.

## Environmental Hatching

Golden Eggs hatch into new Golden Beetles **only when placed near cursed terrain**, such as **Blight**.

This environmental trigger is detected using a surrounding capability check.

---

## Consumption

If the player picks up the egg:

- It **cannot hatch**
- It can instead be **consumed**

Consuming a Golden Egg restores **20 stamina**.

This ensures the system remains deterministic and avoids conflicting behaviours.

---

# Countdown System

A reusable **Countdown system** was implemented to support turn-based lifecycle events.

### Components

```
Countdown
CountdownBehaviour
```

### Supported Mechanics

- NPC decay timers
- Egg production
- Egg hatching
- Future timed events

Because countdown logic is centralized, entities can reuse it **without duplicating state management code**.

---

# BedOfChaos Boss System

The **BedOfChaos** is a complex boss entity designed with a **modular architecture**.

The boss dynamically evolves throughout the fight by **growing additional attack components**.

The boss extends the base `Actor` class and implements a custom `Growable` interface to support controlled growth behaviour.

---

## Boss Structure

```
BedOfChaos
 ├── Branch
 │    ├── Branch
 │    └── Leaf
 └── Leaf
```

Each component contributes to the boss’s **total damage output**.

---

## Branch Growth

Branches are recursive structures that can grow:

- Additional branches
- Leaves

Branches accumulate damage from their children, allowing the boss to grow **stronger over time**.

When a **leaf** is produced, the boss also receives **healing**.

---

## Dynamic Damage System

The boss uses a custom intrinsic weapon:

```
BedOfChaosIntrinsicWeapon
```

The weapon calculates damage based on:

- Base boss damage
- Total damage contributed by all parts

This creates a **dynamic attack scaling system** that evolves during gameplay.

---

## Growth Behaviour

Two classes coordinate the boss growth system.

### GrowBehaviour

Determines whether the boss should grow during a turn.

Example condition:

- Grow when no players are nearby.

### GrowAction

Executes the growth process by creating new branches or leaves.

Separating behaviour selection from execution ensures adherence to the **Single Responsibility Principle**.

---

# NPC Architecture Improvements

Earlier project iterations contained repeated NPC behaviour logic.

To improve maintainability, the architecture was refactored to introduce an **abstract NPC hierarchy**.

### Improvements

- Abstract `NPC` base class
- `AttackableNPC` subclass
- Shared behaviour configuration

This change improved:

- Code reuse
- Maintainability
- Adherence to SOLID principles

---

# SOLID Design Principles

The project architecture emphasizes strong adherence to **SOLID principles**.

## Single Responsibility Principle

Classes are responsible for one task only.

Examples:

- `GrowBehaviour` decides growth
- `GrowAction` performs growth
- `ProduceAction` handles reproduction

---

## Open / Closed Principle

The system is designed to be **extended without modification**.

Examples:

- New egg types
- New behaviours
- New NPC types
- New boss parts

---

## Dependency Inversion

Systems rely on **interfaces rather than concrete implementations**.

Examples include:

```
Eatable
Producible
Curable
Plantable
Growable
```

This allows systems to interact through **contracts rather than specific implementations**.

---

# Running the Game

### 1. Clone the repository

```
git clone <repo-url>
```

### 2. Open the project

Open the project in **IntelliJ IDEA** or **VS Code**.

### 3. Run the game

Run the `Application` class.

---

# What This Project Demonstrates

This project demonstrates strong competency in:

- Object-oriented architecture
- Extensible system design
- Modular gameplay mechanics
- Interface-driven programming
- Behaviour-based AI systems

These design choices allow the game to evolve easily with **new mechanics and features**.
