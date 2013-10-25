package blak.android.example.adapter_attachment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import blak.android.adapters.HolderListAdapter;
import blak.android.adapters.ItemClickListener;
import blak.android.adapters.ItemSubviewClickListener;
import blak.android.example.R;

import java.util.List;

public class AttachmentsAdapter extends HolderListAdapter<Attachment, AttachmentsAdapter.AttachmentHolder> {
    private final ItemSubviewClickListener mEditListener = new ItemSubviewClickListener();

    public AttachmentsAdapter(Context context) {
        this(context, null);
    }

    public AttachmentsAdapter(Context context, List<? extends Attachment> attachments) {
        super(context, attachments);
    }

    public void setEditListener(ItemClickListener editListener) {
        mEditListener.setItemClickListener(editListener);
    }

    @Override
    protected int getResourceId() {
        return R.layout.afs__attachment_item;
    }

    @Override
    protected AttachmentHolder createViewHolder(View view) {
        AttachmentHolder holder = new AttachmentHolder();
        View layout = view.findViewById(R.id.afs_attachment_layout);
        holder.fileNameView = (TextView) view.findViewById(R.id.afs__attachment_filename);
        holder.descriptionView = (TextView) view.findViewById(R.id.afs__attachment_description);
        holder.editBtn = view.findViewById(R.id.afs__attachment_edit_btn);
        return holder;
    }

    @Override
    protected void initView(View view, AttachmentHolder holder, Attachment attachment, int position) {
        holder.fileNameView.setText(attachment.fileName);
        holder.descriptionView.setText(attachment.description);

        holder.editBtn.setTag(position);
        holder.editBtn.setOnClickListener(mEditListener);
    }

    public static class AttachmentHolder {
        TextView fileNameView;
        TextView descriptionView;
        View editBtn;
    }
}
