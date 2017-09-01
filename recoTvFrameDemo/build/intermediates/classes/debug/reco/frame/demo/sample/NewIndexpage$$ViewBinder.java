// Generated code from Butter Knife. Do not modify!
package reco.frame.demo.sample;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewIndexpage$$ViewBinder<T extends reco.frame.demo.sample.NewIndexpage> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427461, "field 'tvTopDate'");
    target.tvTopDate = finder.castView(view, 2131427461, "field 'tvTopDate'");
    view = finder.findRequiredView(source, 2131427460, "field 'ivTopClock'");
    target.ivTopClock = finder.castView(view, 2131427460, "field 'ivTopClock'");
    view = finder.findRequiredView(source, 2131427462, "field 'tvTopTime'");
    target.tvTopTime = finder.castView(view, 2131427462, "field 'tvTopTime'");
    view = finder.findRequiredView(source, 2131427463, "field 'tlMain'");
    target.tlMain = finder.castView(view, 2131427463, "field 'tlMain'");
    view = finder.findRequiredView(source, 2131427464, "field 'vpMainShows'");
    target.vpMainShows = finder.castView(view, 2131427464, "field 'vpMainShows'");
  }

  @Override public void unbind(T target) {
    target.tvTopDate = null;
    target.ivTopClock = null;
    target.tvTopTime = null;
    target.tlMain = null;
    target.vpMainShows = null;
  }
}
