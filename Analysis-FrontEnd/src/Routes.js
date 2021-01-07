import Vue from 'vue';
import Router from 'vue-router';

import Layout from './components/Layout/Layout';
import Login from './pages/Login/Login';
import ErrorPage from './pages/Error/Error';
// Core
import TypographyPage from './pages/Typography/Typography';

// Tables
import TablesBasicPage from './pages/Tables/Basic';

// Main
import AnalyticsPage from './pages/Dashboard/Dashboard';

// Charts
import ChartsPage from './pages/Charts/Charts';

// Ui
import IconsPage from './pages/Icons/Icons';

//Analysis
import AnalysisBasic from './pages/Analysis/Basic';

//Data governance
import DataGovernance from "./pages/DataGovernance/DataGovernance";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '*',
      name: 'Error',
      component: ErrorPage,
    },
    {
      path: '/app',
      name: 'Layout',
      component: Layout,
      children: [
        {
          path: 'dashboard',
          name: 'AnalyticsPage',
          component: AnalyticsPage,
        },
        {
          path: 'components/icons',
          name: 'IconsPage',
          component: IconsPage,
        },
        {
          path: 'components/charts',
          name: 'ChartsPage',
          component: ChartsPage,
        },
        {
          path: 'components/tables',
          name: 'TablesBasicPage',
          component: TablesBasicPage,
        },
        {
          path: 'analysis',
          name: 'Analysis',
          component: AnalysisBasic,
        },
        {
          path: 'components/typography',
          name: 'TypographyPage',
          component: TypographyPage,
        },
        {
          path: 'dataGovernance',
          name: 'DataGovernance',
          component: DataGovernance,
        },
      ],
    },
  ],
});
