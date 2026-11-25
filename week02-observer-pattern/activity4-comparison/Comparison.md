# Comparison of Event Notification Mechanisms in Java

## 1. Introduction

Java provides different mechanisms for implementing event notification systems. These mechanisms allow objects to communicate and respond when changes occur in another object.

The four mechanisms discussed in this activity are Custom Observer, `java.util.Observer`, `PropertyChangeListener`, and JavaFX Properties and Bindings.

Each mechanism has its own advantages, limitations and suitable applications.

---

## 2. Comparison Table

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
|---|---|---|---|---|
| **Push or Pull?** | Both possible | Mainly Pull | Push (old and new values) | Push |
| **Is it deprecated?** | No | Yes, since Java 9 | No | No |
| **Requires inheritance?** | No, uses interfaces | Yes, Observable requires extending a class | No | No |
| **Thread-safe?** | Depends on implementation | Not guaranteed | Yes, PropertyChangeSupport is thread-safe | Generally designed for JavaFX application-thread use |
| **Auto-generates boilerplate?** | No | Partially | Yes, through PropertyChangeSupport | Yes, through built-in properties and bindings |
| **Best for...** | Custom notification systems | Legacy applications | JavaBeans and Swing applications | JavaFX applications |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can send different data types?** | Yes, depending on implementation | Yes, through Object | Yes, through property values | Yes, through typed properties |
| **Recommended today?** | Yes | No | Yes, where appropriate | Yes, for JavaFX applications |

---

## 3. Explanation of the Four Mechanisms

### 3.1 Custom Observer Pattern

The Custom Observer Pattern is implemented by creating our own Subject and Observer interfaces.

The Subject maintains a list of registered observers and notifies them whenever its data changes. Each observer implements an update method that receives the notification.

**Advantages:**
- Provides flexibility because developers control how notifications work.
- Supports multiple observers.
- Does not require extending a particular class.
- Can be customised to support different data types.

**Disadvantages:**
- Developers must write and maintain the notification mechanism themselves.
- The implementation requires additional code.
- Thread safety must be handled when required.

**Best used when:** Developing a normal Java application that requires a customised event notification system.

### 3.2 java.util.Observer and java.util.Observable

These are older Java classes that provide a built-in implementation of the Observer pattern.

The Observable class acts as the Subject, while classes implementing the Observer interface receive notifications through the update() method.

**Advantages:**
- Provides an already established notification mechanism.
- Supports multiple observers.
- Reduces the amount of code needed to implement a basic notification system.

**Disadvantages:**
- Observable is a class, meaning a class extending it cannot extend another class.
- Observers are notified in an unspecified order.
- Developers must call setChanged() before notifyObservers().
- Observers commonly retrieve updated information from the Subject.
- The API has been deprecated since Java 9.

**Best used when:** Understanding or maintaining older Java applications that already use this mechanism. It is not recommended for new projects.

### 3.3 PropertyChangeListener

PropertyChangeListener is part of the java.beans package. It allows objects to notify registered listeners whenever a property changes.

It uses PropertyChangeSupport to manage listeners and send notifications containing the property name, old value and new value.

**Advantages:**
- Automatically manages registered listeners through PropertyChangeSupport.
- Provides both old and new property values.
- Supports multiple listeners.
- Does not require extending a particular class.
- Is still supported in modern Java.

**Disadvantages:**
- It is mainly designed for monitoring changes in object properties.
- Developers must use PropertyChangeSupport to manage notifications.
- It may be unnecessary for applications requiring more specialised event handling.

**Best used when:** Developing JavaBeans or Swing applications where changes to object properties need to be monitored.

### 3.4 JavaFX Properties and Bindings

JavaFX Properties and Bindings provide a modern way of managing changes between objects and graphical user interfaces.

A property can be observed, while a binding allows one value to automatically update when another value changes.

**Advantages:**
- Automatically updates connected interface components when values change.
- Reduces the need to manually write notification code.
- Supports different data types.
- Makes it easier to keep user interface components synchronised.
- Integrates directly with JavaFX applications.

**Disadvantages:**
- Requires knowledge of JavaFX.
- Is mainly suitable for JavaFX applications.
- JavaFX UI-related updates must respect JavaFX application-thread rules.

**Best used when:** Developing modern JavaFX applications that require automatic updates and synchronisation between interface components.

---

## 4. Which One Should You Use?

The choice of an event notification mechanism depends on the type of application being developed and its requirements.

- **For a plain Java project with custom requirements:** Use the Custom Observer Pattern because it provides flexibility and allows developers to control how notifications are handled.

- **For a JavaFX application:** Use JavaFX Properties and Bindings because they provide automatic updates and make it easier to synchronise interface components.

- **For JavaBeans and Swing applications:** Use PropertyChangeListener when you need to monitor changes in object properties and notify registered listeners.

- **For legacy applications:** Understand java.util.Observer and Observable when maintaining existing code, but avoid using them in new projects because they are deprecated.

---

## 5. Self-Check Questions

### Question 1: Which mechanism carries both the old and new value of a property?

**Answer:** PropertyChangeListener. It sends a PropertyChangeEvent containing the property name, old value and new value.

### Question 2: Which mechanism requires a helper class to manage the listener list?

**Answer:** PropertyChangeListener uses PropertyChangeSupport to manage registered listeners and send property change notifications.

### Question 3: Which two mechanisms are recommended for modern Java?

**Answer:** The Custom Observer Pattern and JavaFX Properties and Bindings. They provide flexible and modern approaches to implementing event notifications.

### Question 4: Which mechanism is deprecated and should be avoided?

**Answer:** java.util.Observer and java.util.Observable. They were deprecated in Java 9 because of design limitations and the availability of more flexible alternatives.

---

## 6. Conclusion

The four event notification mechanisms provide different ways for objects to communicate when changes occur. The Custom Observer Pattern offers flexibility and allows developers to create their own notification systems. PropertyChangeListener is useful for monitoring changes in Java object properties, while JavaFX Properties and Bindings simplify communication between graphical interface components. The older java.util.Observer and Observable mechanism has several limitations and is no longer recommended for new applications. Understanding these mechanisms helps developers select the most suitable approach depending on their application requirements.