import { createRouter, createWebHistory } from 'vue-router';
import ActivePage from '../components/active.vue';
import AuthorizationPage from '../components/auth.vue';
import ComputerPage from '../components/computers.vue';
import MonitorsPage from "../components/Monitors.vue";
import AdminPage from '../components/admin.vue';
import {reactive} from "vue";

const routes = [
    {
        path: '/ActiveLayout',
        component: ActivePage
    },
    {
        path: '/',
        component: AuthorizationPage
    },
    {
        path: '/ComputersLayout',
        component: ComputerPage
    },
    {
        path: '/AdminLayout',
        component: AdminPage
    },
    {
        path: '/MonitorsLayout',
        component: MonitorsPage
    },
    // Другие маршруты
];

const router = createRouter({
    history: createWebHistory(),
    routes
});
const layout = reactive({ name: "AuthLayout" });

router.layout = layout;

router.afterEach((to) => {
    if (to.matched.length === 0) {
        return "AuthLayout";
    }

    const layoutName = to.matched[0].components.default.layout;
    if (layoutName) {
        router.layout.name = layoutName;
    }
});

export default router;




