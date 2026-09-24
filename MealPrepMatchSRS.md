# Requirements - MealPrep Match

**Project Name:** MealPrep Match \
**Team:** Jameson Teeters - Provider, Daniel Son - Customer \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-09-15

---

## 1. Overview
**Vision.** MealPrep Match is a food prep subscription service that gives the customer the control to quickly set up a plan to have meals that match their specific diet and busy schedule. The system makes this easy for customers by offering a variety of dietary options that fulfill a variety of requirements that any busybody would need to fit into their daily intake, and our chefs(providers) can offer their curated and high quality meals as options for said customers. 

**Glossary:** 
- **Chef/Nutritionist:** The chef behind the numerous dietary options we offer to customers.
- **Consumer:** A consumer seeking microwave/oven ready meals they don't have to prepare themselves.
- **Profile:** A consumer's information regarding their personal details, blacklisted dietary tags, and any preferences they may have.
- **Subscriptions:** The plan to sign up to so we send packed meals on a weekly basis.

**Primary Users and Roles:**
- **Consumer** - Find meal options aligned with personal and diet goals.
- **Chef/Nutritionist** — Attract clients and manage food options and assigns dietary tags to them.

**Scope (this semester):**
- User profiles (consumers and chef/nutritionists)
- Search and browse packaged meals by diet tags (maybe a proper blacklisting system?) where the provider assigns tags to said meals
- Catalog of packaged meals to choose from (where the meals in catalog are posted by chefs)
- Reviews and ratings for meals

**Out of scope (deferred):**
- Nutrition plans
- Delivery windows
- Calorie tracking
- Diet personalization
- Proper blacklisting system (maybe in immediate scope)

---

## 2. Functional Requirements (User Stories)

### 2.1 Customer Stories
- **US-1 - Register and manage profile to sign up for a subscription**

  _Story:_ As a consumer, I want to create a profile so that I can sign up for a subscription.

  _Acceptance:_
  ```gherkin
  Scenario: Register with valid credentials
    Given I am not registered
    When I provide valid registration details
    Then I should be successfully registered and logged in
    And I can view my profile and subscribe to a meal plan
  ```

- **US-2 - Browse meals by diet tag/category**

  _Story:_ As a consumer, I want to browse meals by dietary tags so that I can quickly find relevant meals.

  _Acceptance:_
  ```gherkin
  Scenario: Browse meals by diet tag/category
    Given I am logged in as a consumer
    When I select a diet archetype
    Then I should see a list of meals that fit in that category
  ```

- **US-3 - Add meals to subscription**

  _Story:_ As a consumer, I want to be able to add meals to my liking to my subscription.

  _Acceptance:_
  ```gherkin
  Scenario: Add a meal to my plan
    Given I am logged in as a consumer
    When I select a meal to add to my plan
    Then I should see that meal be added to my plan 
    And I can view the meal on my meal plan order for the week
  ```

- **US-4 - Write a review after trying a meal**

  _Story:_ As a consumer, I want to write a review after trying a meal to inform others about my experience.

  _Acceptance:_
  ```gherkin
  Scenario: Write a review after trying a meal
    Given I have had a week of meals sent to my house
    When I send a review about one of the meals I had that week
    Then the review should be saved and visible to other customers
  ```

### 2.2 Provider (Trainer) Stories

- **US-5 - Register as a Trainer to Provide Services**

  _Story:_ As a Provider, I want to create my account, so that I can promote and provide my services

  _Acceptance:_
  ```gherkin
  Scenario: Register with valid credentials.
    Given I am not registered.
    When I Provide valid registration details
    Then I should be successfully registered and logged in
    And then I can view my profile and provide meal plans
  ```

- **US-6 - Provide Mealplan options in my catalog**

  _Story:_ As a provider, I want to add meals to my catalog so that customers can see available options

  _Acceptance:_
  ```gherkin
  Scenario: Provide meals in my catalog
    Given I am logged in as a provider
    When I Add available meals to my catalog
    Then the meals should be displayed to any consumer
  ```

- **US-7 - Have Details on my Chef so the customer knows what they make**

  _Story:_ As a provide, I want to have a page detailing what I specify in

  _Acceptance:_
  ```gherkin
  Scenario: Customer wants to know a chefs specialty
    Given I have a valid Provider account
    When the customer clicks on "About Me"
    Then the button will link to a details page about a particular chef.
  ```

- **US-8 - Display Nutritional Details**

  _Story:_ As a Provider, I want to display nutritional information so the customer knows what is in each meal

  _Acceptance:_
  ```gherkin
  Scenario: Display Nutritional Details
    Given I have added a meal to my catalog
    When I click on a specific meal
    Then I should see any nutritional details about that meal
  ```

---

## 3. Non-Functional Requirements
- **Performance:** This app should have no issue loading any pages.
- **Availability/Reliability:** The app should be available when customers and providers need it and should work consistently without any connection issues.
- **Security/Privacy:** The app should keep customer and provider information secure and only allow users to access information specific to them.
- **Usability:** The app should be simple and easy to understand so that customers can find and purchase mealprep plans, while providers can easily create and manage their meal options.

---

## 4. Assumptions, Constraints, and Policies
- Customers and providers will need an account to use the main features of the app.
- Providers are responsible for the meal plans and information they make available.
- Customers are responsible for providing accurate order information.
- Customers should be able to view and manage their orders.
- Providers should be able to view and manage their meal options.
- Providers are responsible for providing accurate nutritional information.
- This app will not be accessible without an internet connection.

---

## 5. Milestones (course-aligned)
- **M1 Requirements** — this file and related stories opened as issues.
- **M2 High-fidelity prototype** — core customer and provider UI flows are fully interactive.
- **M3 Design** — architecture, schema, and API outline.
- **M4 Backend API** — key endpoints and tests.
- **M5 Increment** — at least 2 use cases end-to-end.
- **M6 Final** — complete system and documentation.

---

## 6. Change Management
- User stories can change as this project expands
- Changes will be tracked through GitHub, using issues and linked pull requests.
- Changes to the requirements will also be updated in the SRS.