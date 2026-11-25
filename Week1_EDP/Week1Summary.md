# WEEK 1 SUMMARY: EVENT-DRIVEN PROGRAMMING

## Introduction

This week introduced me to the fundamentals of Event-Driven Programming (EDP). I learned how applications respond to user actions and other events instead of following only a fixed sequence of instructions.

## What I Learned

### 1. Event-Driven Programming Components

I learned about the main components of event-driven programming:

- **Event Source:** The component that generates an event, such as a button.
- **Event:** An action or occurrence, such as clicking a button.
- **Event Listener:** Monitors an event source and waits for a particular event.
- **Event Handler:** The code that executes when an event occurs.

### 2. Event Loop and Event Queue

I learned that events are placed in an event queue and processed by the event loop. The event loop takes events from the queue and sends them for processing. I also understood why long-running operations should not block the event loop.

### 3. Procedural vs Event-Driven Programming

I compared procedural programming with event-driven programming. Procedural programming follows a sequence of instructions, while event-driven programming responds to events such as clicks and keyboard input. I also created and tested calculator applications using both approaches.

### 4. Debugging Event Handlers

I identified and fixed four common problems:

- Blocking the Event Dispatch Thread (EDT).
- Missing event listeners.
- Updating GUI components from the wrong thread.
- Memory leaks caused by retaining unnecessary references.

I learned that Swing GUI updates should be performed on the Event Dispatch Thread.

### 5. Designing an Event-Driven System

I designed a Smart Home Controller using Java Swing. The system allows users to control lights, security mode, door operations and room temperature. I identified its event sources, events and event handlers and demonstrated how the system responds to user interactions.

## Challenges Faced

Some of the challenges I encountered involved understanding how event listeners work, identifying errors in event handlers and ensuring that GUI operations run on the correct thread. Debugging the applications helped me understand these concepts better.

## Conclusion

Overall, this week helped me understand how event-driven applications work. I gained practical experience creating interactive Java applications, handling events, debugging common problems and designing a system that responds to user actions. I now have a better understanding of how event-driven programming is used in real-world applications.