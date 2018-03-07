package vn.quankundeptrai.banana.ui.createevent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionLegs;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionRoutes;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleLatLng;
import vn.quankundeptrai.banana.enums.Density;
import vn.quankundeptrai.banana.enums.VehicleSpeed;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.utils.GeneralUtils;

/**
 * Created by TQN on 3/6/18.
 */

public class CreateNewEventActivity extends BaseActivity<CreateNewEventPresenter> implements CreateNewEventMvpView, View.OnClickListener {
    private GoogleDirectionRoutes routes;
    private Density density = Density.CROWDED;
    private VehicleSpeed carSpeed = VehicleSpeed.SLOW, bikeSpeed = VehicleSpeed.SLOW;
    private boolean hasRain = false, hasFlood = false, hasAccident = false, shouldTravel = false, hasPolice = false;
    private TextView densitySelector, carSpeedSelector, bikeSpeedSelector, hasRainSelector, hasAccidentSelector, hasFloodSelector, hasPoliceSelector, recommendationSelector;
    private AlertDialog.Builder picker;
    private String[] yesNoOptions, vehiclesOptions;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_create_event;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected CreateNewEventPresenter onCreatePresenter() {
        return new CreateNewEventPresenter();
    }

    @Override
    protected void initialView() {
        routes = (GoogleDirectionRoutes) getIntent().getSerializableExtra(ExtraKeys.NEW_EVENT);
        yesNoOptions = getResources().getStringArray(R.array.yesNo);
        vehiclesOptions = getResources().getStringArray(R.array.vehicleSpeed);
        picker = new AlertDialog.Builder(this);
        picker.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        (densitySelector = mainView.findViewById(R.id.densitySelector)).setOnClickListener(this);
        (carSpeedSelector = mainView.findViewById(R.id.carSpeed)).setOnClickListener(this);
        (bikeSpeedSelector = mainView.findViewById(R.id.motorbikeSelector)).setOnClickListener(this);
        (hasRainSelector = mainView.findViewById(R.id.hasRain)).setOnClickListener(this);
        (hasAccidentSelector = mainView.findViewById(R.id.hasAccident)).setOnClickListener(this);
        (hasFloodSelector = mainView.findViewById(R.id.hasFlood)).setOnClickListener(this);
        (hasPoliceSelector = mainView.findViewById(R.id.hasPolice)).setOnClickListener(this);
        (recommendationSelector = mainView.findViewById(R.id.recommendation)).setOnClickListener(this);

        mainView.findViewById(R.id.select).setOnClickListener(this);
        mainView.findViewById(R.id.unselect).setOnClickListener(this);

        ((TextView) mainView.findViewById(R.id.startAddress)).setText(routes.getLegs().get(0).getStartAddress());
        ((TextView) mainView.findViewById(R.id.distance)).setText(routes.getLegs().get(0).getDistance().getDistance());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select:
                showLoading();
                GoogleDirectionLegs legs = routes.getLegs().get(0);
                GoogleLatLng startLatLng = legs.getStartLocation();
                GoogleLatLng endLatLng = legs.getEndLocation();
                getPresenter().postEvent(
                        legs.getStartAddress(),
                        startLatLng, endLatLng,
                        GeneralUtils.getDistance(startLatLng, endLatLng),
                        density.ordinal(), carSpeed.ordinal(), bikeSpeed.ordinal(),
                        hasRain, hasFlood, hasAccident, hasPolice, shouldTravel);
                break;
            case R.id.unselect:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.cancel_post))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.densitySelector:
                final String[] densities = getResources().getStringArray(R.array.density);
                picker.setTitle(getString(R.string.density))
                        .setSingleChoiceItems(densities, density.ordinal(), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        density = Density.values()[which];
                                        densitySelector.setText(densities[which]);
                                        dialog.dismiss();
                                    }
                                }
                        );
                picker.show();
                break;
            case R.id.carSpeed:
                picker.setTitle(getString(R.string.car));
                picker.setSingleChoiceItems(vehiclesOptions, carSpeed.ordinal(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                carSpeed = VehicleSpeed.values()[which];
                                carSpeedSelector.setText(vehiclesOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.motorbikeSelector:
                picker.setTitle(getString(R.string.motorbike));
                picker.setSingleChoiceItems(vehiclesOptions, bikeSpeed.ordinal(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bikeSpeed = VehicleSpeed.values()[which];
                                bikeSpeedSelector.setText(vehiclesOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.hasRain:
                picker.setTitle(getString(R.string.rain));
                picker.setSingleChoiceItems(yesNoOptions, hasRain ? 1 : 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hasRain = which == 1;
                                hasRainSelector.setText(yesNoOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.hasFlood:
                picker.setTitle(getString(R.string.flood));
                picker.setSingleChoiceItems(yesNoOptions, hasFlood ? 1 : 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hasFlood = which == 1;
                                hasFloodSelector.setText(yesNoOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.hasAccident:
                picker.setTitle(getString(R.string.accident));
                picker.setSingleChoiceItems(yesNoOptions, hasAccident ? 1 : 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hasAccident = which == 1;
                                hasAccidentSelector.setText(yesNoOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.hasPolice:
                picker.setTitle(getString(R.string.police));
                picker.setSingleChoiceItems(yesNoOptions, hasPolice ? 1 : 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hasPolice = which == 1;
                                hasPoliceSelector.setText(yesNoOptions[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
            case R.id.recommendation:
                picker.setTitle(getString(R.string.warning));
                final String[] recommendation = getResources().getStringArray(R.array.recommendation);
                picker.setSingleChoiceItems(recommendation, shouldTravel ? 0 : 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                shouldTravel = which == 0;
                                recommendationSelector.setText(recommendation[which]);
                                dialog.dismiss();
                            }
                        }
                );
                picker.show();
                break;
        }
    }

    @Override
    public void onPostEventDone(boolean isSuccess) {
        hideLoading();
        if (isSuccess) {
            Toast.makeText(this, getString(R.string.post_success), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }
}
