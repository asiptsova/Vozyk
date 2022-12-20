package com.application.vozyk.ui.relax;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.vozyk.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Relax extends Fragment {

        TabLayout tabLayout;
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
            final View root = inflater.inflate(R.layout.activity_relax, container, false);

            setupViewpager(root.findViewById(R.id.viewpager));

            tabLayout = root.findViewById(R.id.tab);
            tabLayout.setupWithViewPager(root.findViewById(R.id.viewpager));
            return root;
        }

        private void setupViewpager(ViewPager viewPager) {
            ViewpagerAdapter adapter = new ViewpagerAdapter(getChildFragmentManager());
            adapter.addFragment(new Breathe(),  this.getResources().getString(R.string.Breathe));
            adapter.addFragment(new Meditate(),  this.getResources().getString(R.string.Meditate));
            viewPager.setAdapter(adapter);
        }

        public static class ViewpagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> fragmentList = new ArrayList<>();
            private final List<String> fragmentTitleList = new ArrayList<>();

            public ViewpagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                fragmentList.add(fragment);
                fragmentTitleList.add(title);
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentTitleList.get(position);
            }
        }
    }
