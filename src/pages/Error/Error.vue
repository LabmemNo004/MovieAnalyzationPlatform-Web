<template>
  <div class="error-page">
    <b-container>
      <div class="errorContainer mx-auto">
        <h1 class="errorCode">404</h1>
        <p class="errorInfo">
            Opps, it seems that this page does not exist.
        </p>
        <p class="errorHelp mb-3">
            Search Pages Path—— like "/app/dashboard".
        </p>
        <b-form>
          <b-form-group>
            <b-form-input class="input-no-border" type="text" placeholder="/app/dashboard" v-model="routeName"/>
          </b-form-group>
          <router-link tag="a" :to=routeName>
            <b-button class="errorBtn" type="submit" variant="success">
                Search <i class="la la-search ml-xs" />
            </b-button>
          </router-link>
        </b-form>
      </div>
      <footer class="pageFooter">
        Amazon Movies Analyze Dashboard - Made by <a href="https://github.com/LabmemNo004" rel="nofollow noopener noreferrer" target="_blank">LabmemNo004</a>
      </footer>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'ErrorPage',
  data() {
    return {
      routeName: '/app/dashboard'
    }
  }
};
</script>

<style src="./Error.scss" lang="scss" scoped />
