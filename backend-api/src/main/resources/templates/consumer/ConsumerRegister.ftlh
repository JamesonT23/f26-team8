<#import "/spring.ftl" as spring/>
<#macro field path label type="text" placeholder="" required=false>
  <@spring.bind path/>
  <div class="form-group">
    <label for="${spring.status.expression}">${label}</label>
    <input type="${type}" id="${spring.status.expression}" name="${spring.status.expression}"
           placeholder="${placeholder}"
           <#if type != "password">value="${(spring.status.value)!''}"</#if>
           <#if required>required</#if>>
    <#list spring.status.errorMessages as e>
      <div class="text-danger small">${e}</div>
    </#list>
  </div>
</#macro>
<#-- Tags currently selected, so checkboxes come back ticked -->
<#assign selectedDiet = []>
<#list form.preferredTags as t><#assign selectedDiet += [t?string]></#list>
<#assign selectedBlack = []>
<#list form.blacklistedTags as t><#assign selectedBlack += [t?string]></#list>
<#assign pageTitle=registering?then("Register as a Customer", "Edit Your Profile")>
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">

    <section class="section">
      <div class="container" style="max-width: 600px;">
        <div class="card">
          <div class="card-body">

            <#if registering>
              <h2 class="text-center">Become a MealPrep Match Customer</h2>
              <p class="text-center text-muted">
                Register to access the best meal plans from our providers and apply for a subscription.
              </p>
            <#else>
              <h2 class="text-center">Edit Your Profile</h2>
              <p class="text-center text-muted">Update your details and food preferences.</p>
            </#if>

            <form method="post" action="${formAction}">

              <h3>Personal Information</h3>

              <@field "form.name" "Full Name" "text" "John Smith" true/>
              <@field "form.email" "Email Address" "email" "john@example.com" true/>
              <@field "form.phoneNumber" "Phone Number (optional)" "text" "336-555-0123"/>
              <#if registering>
                <@field "form.password" "Password (at least 8 characters)" "password" "" true/>
              </#if>

              <h3>Customer Information</h3>

              <div class="form-group">
                <label>Dietary Preferences</label>
              </div>
              <#list allDietTags as tag>
                <div class="form-check">
                  <label>
                    <input type="checkbox" name="preferredTags" value="${tag}"
                           <#if selectedDiet?seq_contains(tag?string)>checked</#if>>
                    ${tag?string?replace("_", " ")?lower_case?capitalize}
                  </label>
                </div>
              </#list>

              <div class="form-group" style="margin-top: 1rem;">
                <label>Ingredients / Foods to Avoid</label>
              </div>
              <#list allBlacklistTags as tag>
                <div class="form-check">
                  <label>
                    <input type="checkbox" name="blacklistedTags" value="${tag}"
                           <#if selectedBlack?seq_contains(tag?string)>checked</#if>>
                    ${tag?string?replace("_", " ")?lower_case?capitalize}
                  </label>
                </div>
              </#list>

              <button type="submit" class="btn btn-primary register-button">
                ${registering?then("Register as Customer", "Save Changes")}
              </button>
            </form>

            <p class="text-center login-text">
              <#if registering>
                Already have an account?
                <a href="/login.html" style="color: black; font-weight: 500;">Login here!</a>
              <#else>
                <a href="/ui/consumers/profile" style="color: black; font-weight: 500;">Cancel</a>
              </#if>
            </p>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>

</html>
