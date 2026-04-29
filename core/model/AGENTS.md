# Module Agent Instructions: :core:model

## 🎯 Purpose
This is a pure Kotlin module containing the application's domain models. It should have no dependencies on Android or specific data frameworks (like Firebase).

## 🛡 Guidelines
- **Immutability**: Use `data class` with `val` properties.
- **Default Values**: Provide default values for all properties to allow Firestore's no-arg constructor requirement.
- **Precision**: Monetary values must be `Long` (cents). Timestamps must be `Long` (milliseconds).
- **Documentation**: Use KDoc to explain units and property intent.
- **Serialization**: Annotate classes with `@JsonClass(generateAdapter = true)` for Moshi KSP.

## 📦 Current Models
- `Transaction`: Represents a financial entry with value, currency, type, timestamp, and category.
