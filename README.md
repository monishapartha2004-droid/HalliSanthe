# 🛒 Halli-Santhe Digital
### *Vocal for Local — Bringing the Village Market to Your Screen*
 
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Language](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Firestore%20%7C%20Auth-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![IDE](https://img.shields.io/badge/IDE-Android%20Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)
![Status](https://img.shields.io/badge/Status-Final%20Year%20Project-orange?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
 
> **"Empowering rural artisans with digital commerce — one Haaat at a time."**
 
</div>
---
 
## 📖 Project Overview
 
**Halli-Santhe Digital** is a mobile marketplace application that digitizes the age-old tradition of *Halli Santhe* — the vibrant weekly village markets (*haaat*) of rural Karnataka. The platform bridges the gap between skilled local artisans and modern buyers, enabling real-time product discovery, seamless ordering, and direct artisan-to-buyer communication — all from a smartphone.
 
This project was developed as a **Final Year Engineering Project** to demonstrate real-world Android development skills, Firebase cloud integration, and a deep understanding of rural digital transformation needs.
 
---
 
## ❗ Problem Statement
 
Rural artisans and local vendors in Karnataka — makers of traditional handicrafts, wooden toys, organic produce, and handloom goods — are largely invisible to urban and online buyers due to:
 
- 📵 **No digital presence** — most operate only in weekly physical markets
- 🤝 **Middlemen dependency** — reducing artisan earnings significantly
- 🌍 **Geographic constraints** — buyers must physically travel to access local goods
- 📦 **No order tracking** — no transparency once a transaction is initiated
- 📣 **Zero marketing channels** — word of mouth remains the only outreach tool
---
 
## 💡 Solution Approach
 
Halli-Santhe Digital provides a **dual-role mobile marketplace** — one interface for Artisans (sellers) and another for Buyers — backed by Firebase's real-time cloud infrastructure.
 
- Artisans list their products, manage orders, and share a WhatsApp contact directly
- Buyers browse, discover, and place orders with full delivery tracking
- The app respects the cultural identity of *Halli Santhe* with a "Friday Haaat Day" special delivery feature
---
 
## ✨ Features
 
### 👨‍🎨 Artisan (Seller) Features
- 🔐 Secure Firebase Authentication (email & password login)
- 📊 Artisan Dashboard with product and order count summary
- ➕ Add new products with images, descriptions, category, and pricing
- 📋 View and manage incoming orders (PENDING / CONFIRMED)
- 📱 Register WhatsApp number for direct buyer communication
- 🚪 Secure logout
### 🛍️ Buyer Features
- 🔎 Browse all listed local artisan products
- 📦 View product details: name, price, seller info, and description
- 🛒 Place orders with quantity and delivery location
- 📍 Track order journey in real-time (Placed → Confirmed → Out for Delivery → Delivered)
- 💬 Contact seller directly via WhatsApp integration
- 🔗 Share product links with friends and family
- 📁 View full order history with status updates
### 🎯 Special Platform Features
- 🗓️ **Friday Haaat Day** — special free delivery promotion
- 🚚 **Flexible Delivery Options** — Self Pickup, Local Delivery (within 10km), Courier (₹50–100)
- 🔥 Firebase Firestore real-time data sync
- 🖼️ Glide-powered fast image loading
---
 
## 🏗️ App Architecture
 
The app follows a **role-based single-activity architecture** with Firebase as the backend, structured around two distinct user flows:
 
```
Halli-Santhe Digital
│
├── Auth Layer (Firebase Authentication)
│   ├── Role Selection Screen (Artisan / Buyer)
│   └── Login / Register Screen
│
├── Artisan Flow
│   ├── Artisan Dashboard (Stats, WhatsApp, Navigation)
│   ├── Add Product Screen
│   └── My Orders Screen (Artisan view)
│
└── Buyer Flow
    ├── Product Listing / Browse Screen
    ├── Product Detail Screen (Place Order / Share / Contact)
    └── Order Tracking Screen (4-step journey)
```
 
**Data Flow:**
```
User Action → ViewModel / Activity → Firebase Firestore → Real-time UI Update
```
 
---
 
## 🛠️ Technology Stack
 
| Layer | Technology | Purpose |
|---|---|---|
| **Language** | Kotlin | Primary development language |
| **IDE** | Android Studio | Build, debug, and deploy |
| **UI** | XML Layouts | Screen design and component layout |
| **Authentication** | Firebase Auth | Secure email/password user login |
| **Database** | Firebase Firestore | Real-time cloud NoSQL database |
| **Image Loading** | Glide | Efficient image fetching and caching |
| **Communication** | WhatsApp Intent | Artisan-Buyer direct messaging |
| **Sharing** | Android Share Sheet | Product sharing across apps |
| **Build Tool** | Gradle | Dependency and build management |
 
---
 
## 🔥 Firebase Integration
 
Firebase powers the entire backend of Halli-Santhe Digital:
 
### Authentication
```kotlin
FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
    .addOnSuccessListener { /* navigate to dashboard */ }
    .addOnFailureListener { /* show error */ }
```
 
### Firestore Collections Structure
 
```
firestore/
├── users/
│   └── {uid}/
│       ├── email
│       ├── role ("artisan" | "buyer")
│       └── whatsappNumber
│
├── products/
│   └── {productId}/
│       ├── name
│       ├── price
│       ├── category
│       ├── description
│       ├── imageUrl
│       └── sellerEmail
│
└── orders/
    └── {orderId}/
        ├── productName
        ├── quantity
        ├── totalPrice
        ├── buyerEmail
        ├── sellerEmail
        ├── location
        └── status ("PENDING" | "CONFIRMED" | "OUT_FOR_DELIVERY" | "DELIVERED")
```
 
### Real-time Listener Example
```kotlin
db.collection("orders")
  .whereEqualTo("buyerEmail", currentUser?.email)
  .addSnapshotListener { snapshot, _ ->
      snapshot?.documents?.forEach { doc ->
          // update UI in real-time
      }
  }
```
 
---
 
## 📸 App Screenshots
 
| Screen | Description |
|---|---|
| ![Login](screenshots/login.png) | **Login / Role Selection** — Choose Artisan or Buyer |
| ![Dashboard](screenshots/dashboard.png) | **Artisan Dashboard** — Stats, WhatsApp setup, actions |
| ![Orders](screenshots/my_orders.png) | **My Orders** — View order history with status |
| ![Product Detail](screenshots/product_detail.png) | **Product Detail** — Info, Share, Contact, Place Order |
| ![Track Order](screenshots/track_order.png) | **Order Tracking** — 4-step delivery journey |
 
---
 
## ⚙️ Installation & Setup Guide
 
### Prerequisites
- Android Studio **Hedgehog (2023.1.1)** or later
- JDK 11 or higher
- Android device or emulator running **API 24+** (Android 7.0+)
- A Google account for Firebase Console access
---
 
### 1. Clone the Repository
 
```bash
git clone https://github.com/your-username/halli-santhe-digital.git
cd halli-santhe-digital
```
 
### 2. Open in Android Studio
 
```
File → Open → Select the cloned project folder → Click OK
```
 
Wait for Gradle sync to complete.
 
---
 
### 3. Firebase Setup
 
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project: **Halli-Santhe-Digital**
3. Add an **Android app** with your package name (e.g., `com.example.hallisanthedigital`)
4. Download `google-services.json` and place it in the `/app` directory:
```
app/
└── google-services.json   ← place here
```
 
5. Enable **Email/Password Authentication** under Authentication → Sign-in methods
6. Create **Firestore Database** in test mode (update rules before production)
---
 
### 4. Firestore Security Rules (Development)
 
```
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```
 
---
 
### 5. Build & Run
 
```bash
# Sync Gradle dependencies
./gradlew dependencies
 
# Build debug APK
./gradlew assembleDebug
 
# Install on connected device
./gradlew installDebug
```
 
Or press **▶ Run** in Android Studio.
 
---
 
## 📁 Folder Structure
 
```
app/
├── manifests/
│   └── AndroidManifest.xml
├── java/com/example/hallisanthedigital/
│   ├── LoginActivity.kt              # Auth entry point
│   ├── RoleSelectionActivity.kt      # Artisan / Buyer selection
│   ├── ArtisanDashboardActivity.kt   # Artisan home
│   ├── AddProductActivity.kt         # Product listing form
│   ├── ArtisanOrdersActivity.kt      # Artisan's order management
│   ├── BuyerHomeActivity.kt          # Product browse screen
│   ├── ProductDetailActivity.kt      # Product info & actions
│   └── TrackOrderActivity.kt         # Order journey tracker
├── res/
│   ├── layout/                       # XML UI files
│   ├── drawable/                     # Icons and backgrounds
│   ├── values/
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   └── themes.xml
└── build.gradle
```
 
---
 
## 🚀 Future Enhancements
 
| Feature | Priority | Description |
|---|---|---|
| 🌐 Kannada Language Support | High | Full UI in Kannada for rural users |
| 🔔 Push Notifications | High | Firebase Cloud Messaging for order alerts |
| 💳 UPI Payment Integration | High | In-app payment via Razorpay / PhonePe |
| ⭐ Ratings & Reviews | Medium | Buyer reviews for artisans and products |
| 🗺️ Map-based Discovery | Medium | Find artisans near you using Google Maps |
| 📊 Sales Analytics | Medium | Revenue charts for artisan dashboard |
| 🤖 AI Product Suggestions | Low | ML Kit–powered personalized recommendations |
| 🖼️ Multi-image Upload | Low | Multiple product photos per listing |
| 🧑‍🤝‍🧑 Artisan Communities | Low | Group chats for craft guilds |
 
---
 
## 🧩 Challenges Faced
 
- **Real-time Firestore sync** — ensuring UI updates immediately without full page reloads required careful snapshot listener management
- **Role-based navigation** — designing a clean flow where artisans and buyers never see each other's screens required careful intent and back-stack handling
- **Image upload pipeline** — integrating Firebase Storage with Glide for smooth product image loading across varying network conditions
- **WhatsApp deep-link formatting** — constructing proper `wa.me` URIs with pre-filled messages for one-tap seller contact
- **Order status state machine** — managing PENDING → CONFIRMED → OUT_FOR_DELIVERY → DELIVERED transitions reliably in Firestore
---
 
## 📚 Learning Outcomes
 
- ✅ Built a full-stack Android app using **Kotlin + Firebase** from scratch
- ✅ Mastered **Firebase Firestore** for real-time NoSQL cloud data management
- ✅ Implemented **Firebase Authentication** for secure dual-role login
- ✅ Designed role-based UX flows for two distinct user personas
- ✅ Learned **Glide** for performant image loading and memory management
- ✅ Gained hands-on experience with **Intent-based sharing and WhatsApp integration**
- ✅ Understood the design challenges of building for **low-digital-literacy users** in rural contexts
- ✅ Practised clean **Kotlin idioms**: extension functions, lambda expressions, and null safety
---
 
## 🌾 Conclusion
 
Halli-Santhe Digital is more than a college project — it is a meaningful step toward **rural digital inclusion**. By providing artisans with a free, simple-to-use digital storefront, the app reduces their dependence on middlemen, expands their reach beyond geographic boundaries, and preserves India's rich tradition of local craft markets in a modern form.
 
The project demonstrates a complete, production-ready Android application with real Firebase cloud infrastructure, clean architecture, and thoughtful UX designed for real users with real needs.
 
---
 
## 🤝 Contributing
 
Contributions are welcome! Here's how to get started:
 
```bash
# 1. Fork the repository
# 2. Create your feature branch
git checkout -b feature/AmazingFeature
 
# 3. Commit your changes
git commit -m 'Add some AmazingFeature'
 
# 4. Push to the branch
git push origin feature/AmazingFeature
 
# 5. Open a Pull Request
```
 
Please ensure your code follows Kotlin style guidelines and includes comments for complex logic.
 
---
 
## 📄 License
 
This project is licensed under the **MIT License**.
 
```
MIT License
 
Copyright (c) 2026 Halli-Santhe Digital
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction.
```
 
See [LICENSE](LICENSE) for full details.
 
---
 
## 👨‍💻 Author
 
<div align="center">
**Developed for rural India**
 
| | |
|---|---|
| 🎓 **Degree** | B.E. / B.Tech — Computer Science & Engineering |
| 📅 **Year** | 2025–2026 |
 
---
 
*"Digitizing tradition. Empowering artisans. Vocal for Local."*
 
⭐ **Star this repo** if you found it helpful or inspiring!
 
</div>
