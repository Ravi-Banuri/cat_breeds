package com.catbreeds.example.presentation.ui.features.catbreeds.viewModel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class SharedDataRepository_Factory implements Factory<SharedDataRepository> {
  @Override
  public SharedDataRepository get() {
    return newInstance();
  }

  public static SharedDataRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SharedDataRepository newInstance() {
    return new SharedDataRepository();
  }

  private static final class InstanceHolder {
    private static final SharedDataRepository_Factory INSTANCE = new SharedDataRepository_Factory();
  }
}
